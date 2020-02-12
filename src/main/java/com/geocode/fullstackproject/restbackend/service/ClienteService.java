package com.geocode.fullstackproject.restbackend.service;

import java.util.List;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.dto.ClienteDTO;
import com.geocode.fullstackproject.restbackend.domain.enums.TipoCliente;
import com.geocode.fullstackproject.restbackend.repository.ClienteRepository;
import com.geocode.fullstackproject.restbackend.service.exceptions.EntidadeNaoEncontradaException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * ClienteService
 */
@Service
public class ClienteService {

  final private ClienteRepository repository;

  @Autowired
  public ClienteService(ClienteRepository repository) {
    this.repository = repository;
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

  public Cliente save(Cliente cliente) {
    return repository.save(cliente);
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
    cliente.setCpfouCnpj(cliNewData.getCpfouCnpj());
    cliente.setTipo(cliNewData.getTipo());
  }

  public Cliente fromDTO(ClienteDTO dto) {
    return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), dto.getCpfouCnpj(),
        TipoCliente.toEnum(dto.getTipo()));
  }

}
