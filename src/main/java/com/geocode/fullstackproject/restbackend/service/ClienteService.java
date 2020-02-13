package com.geocode.fullstackproject.restbackend.service;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Cidade;
import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.Endereco;
import com.geocode.fullstackproject.restbackend.domain.dto.ClienteDTO;
import com.geocode.fullstackproject.restbackend.domain.dto.ClienteNewDTO;
import com.geocode.fullstackproject.restbackend.domain.enums.TipoCliente;
import com.geocode.fullstackproject.restbackend.repository.CidadeRepository;
import com.geocode.fullstackproject.restbackend.repository.ClienteRepository;
import com.geocode.fullstackproject.restbackend.repository.EnderecoRepository;
import com.geocode.fullstackproject.restbackend.service.exceptions.EntidadeNaoEncontradaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClienteService
 */
@Service
public class ClienteService {

  final private ClienteRepository repository;
  final private EnderecoRepository enderecoRepository;
  final private CidadeRepository cidadeRepository;

  @Autowired
  public ClienteService(ClienteRepository repository, EnderecoRepository enderecoRepository,
      CidadeRepository cidadeRepository) {
    this.repository = repository;
    this.enderecoRepository = enderecoRepository;
    this.cidadeRepository = cidadeRepository;
  }

  public Cliente findById(Long id) {
    return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(
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

  public Cliente update(Long id, Cliente catNewData) {
    Cliente cliente = findById(id);
    updateData(cliente, catNewData);
    return repository.save(cliente);
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
    cliente.setCpfOuCnpj(cliNewData.getCpfOuCnpj());
    cliente.setTipo(cliNewData.getTipo());
  }

  public Cliente fromDTO(ClienteDTO dto) {
    return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), dto.getCpfouCnpj(),
        TipoCliente.toEnum(dto.getTipo()));
  }

  public Cliente fromDTO(ClienteNewDTO dto) {
    Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(),
        TipoCliente.toEnum(dto.getTipo()));
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

}
