package com.geocode.fullstackproject.restbackend.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.geocode.fullstackproject.restbackend.domain.Cliente;
import com.geocode.fullstackproject.restbackend.domain.dto.ClienteDTO;
import com.geocode.fullstackproject.restbackend.domain.dto.ClienteNewDTO;
import com.geocode.fullstackproject.restbackend.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "clientes")
public class ClienteResource {

  private final ClienteService service;

  @Autowired
  public ClienteResource(ClienteService service) {
    this.service = service;
  }

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping
  public ResponseEntity<List<Cliente>> findAll() {
    List<Cliente> clientes = service.findAll();
    return ResponseEntity.ok().body(clientes);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Cliente> findById(@PathVariable Long id) {
    Cliente cliente = service.findById(id);
    return ResponseEntity.ok().body(cliente);
  }

  @GetMapping(value = "/email")
  public ResponseEntity<Cliente> findByEmail(@RequestParam(value="value") String email) {
    Cliente cliente = service.findByEmail(email);
    return ResponseEntity.ok().body(cliente);
  }

  @PreAuthorize("hasAnyRole('ADMIN')")
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping(value = "/pages")
  public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPages,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
    Page<Cliente> clientes = service.findPage(page, linesPerPages, direction, orderBy);
    Page<ClienteDTO> PageDTOs = clientes.map(c -> new ClienteDTO(c));
    return ResponseEntity.ok().body(PageDTOs);
  }

  @PostMapping
  public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteNewDTO newDTO) {
    Cliente entity = service.fromDTO(newDTO);
    entity = service.insert(newDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
    return ResponseEntity.created(uri).body(entity);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO ClienteWithNewData) {
    Cliente cliente = service.fromDTO(ClienteWithNewData);
    cliente = service.update(id, cliente);
    return ResponseEntity.ok().body(cliente);
  }

  @PostMapping(value = "/picture")
  public ResponseEntity<String> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
    URI uri = service.uploadProfilePicture(file);
    return ResponseEntity.created(uri).body(uri.toString());
  }

}
