package com.geocode.fullstackproject.restbackend.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Cidade;
import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.Endereco;
import com.geocode.fullstackproject.restbackend.domain.dto.ClienteDTO;
import com.geocode.fullstackproject.restbackend.domain.dto.ClienteNewDTO;
import com.geocode.fullstackproject.restbackend.domain.enums.Perfil;
import com.geocode.fullstackproject.restbackend.domain.enums.TipoCliente;
import com.geocode.fullstackproject.restbackend.repository.CidadeRepository;
import com.geocode.fullstackproject.restbackend.repository.ClienteRepository;
import com.geocode.fullstackproject.restbackend.repository.EnderecoRepository;
import com.geocode.fullstackproject.restbackend.security.UserSS;
import com.geocode.fullstackproject.restbackend.service.exceptions.AuthorizationException;
import com.geocode.fullstackproject.restbackend.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClienteService
 */
@Service
public class ClienteService {

  final private ClienteRepository repository;
  final private EnderecoRepository enderecoRepository;
  final private CidadeRepository cidadeRepository;
  final private BCryptPasswordEncoder bCrypt;
  final private S3Service s3service;
  final private ImageService imageService;

  @Value("${img.prefix.client.profile}")
  private String imgPrefix;

  @Value("${img.profile.size}")
  private Integer imgSize;

  @Autowired
  public ClienteService(ClienteRepository repository, EnderecoRepository enderecoRepository,
      CidadeRepository cidadeRepository, BCryptPasswordEncoder bCrypt, S3Service s3Service, ImageService imageService) {
    this.repository = repository;
    this.enderecoRepository = enderecoRepository;
    this.cidadeRepository = cidadeRepository;
    this.bCrypt = bCrypt;
    this.s3service = s3Service;
    this.imageService = imageService;
  }

  public Cliente findById(Long id) {

    UserSS user = UserService.authenticatedUser();
    if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
      throw new AuthorizationException("Acesso Negado");
    }

    return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
        String.format("Objeto não encontrado. Id: %d. Tipo: %s", id, Cliente.class.getName())));
  }

  public List<Cliente> findAll() {
    return repository.findAll();
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Transactional
  public Cliente insert(ClienteNewDTO cliente) {
    Cliente entity = fromDTO(cliente);
    enderecoRepository.saveAll(entity.getEnderecos());
    return repository.save(entity);
  }

  public Cliente update(Long id, Cliente clienteNewData) {
    Cliente entity = findById(id);
    updateData(entity, clienteNewData);
    return repository.save(entity);
  }

  public Page<Cliente> findPage(Integer page, Integer linesPerPages, String direction, String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
    return repository.findAll(pageRequest);
  }

  /**
   * Métodos auxiliares
   */
  private void updateData(Cliente cliente, Cliente cliNewData) {
    cliente.setNome(cliNewData.getNome());
    cliente.setEmail(cliNewData.getEmail());
  }

  public Cliente fromDTO(ClienteDTO dto) {
    return new Cliente(dto.getId(), dto.getNome(), dto.getEmail());
  }

  public Cliente fromDTO(ClienteNewDTO dto) {
    Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(),
        TipoCliente.toEnum(dto.getTipo()), bCrypt.encode(dto.getSenha()));
    Cidade cidade = cidadeRepository.findById(dto.getCidadeId()).get();
    Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(),
        dto.getCep(), cidade, cliente);
    cliente.getEnderecos().add(end);
    cliente.getTelefones().add(dto.getTelefone1());
    if (dto.getTelefone2() != null)
      cliente.getTelefones().add(dto.getTelefone2());
    if (dto.getTelefone3() != null)
      cliente.getTelefones().add(dto.getTelefone3());
    return cliente;
  }

  public URI uploadProfilePicture(MultipartFile mpFile) {
    UserSS user = UserService.authenticatedUser();
    if (user == null) {
      throw new AuthorizationException("Acesso Negado");
    }
    
    BufferedImage jpgImage = imageService.getJpgImageFromFile(mpFile);
    jpgImage = imageService.cropSquare(jpgImage);
    jpgImage = imageService.resize(jpgImage, imgSize);
    
    String fileName = imgPrefix + user.getId() + ".jpg";

    return s3service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
  }

}
