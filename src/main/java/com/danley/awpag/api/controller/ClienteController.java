package com.danley.awpag.api.controller;

import com.danley.awpag.domain.exception.NegocioException;
import com.danley.awpag.domain.model.Cliente;
import com.danley.awpag.domain.repository.ClienteRepository;
import com.danley.awpag.domain.service.CadastroClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor // lombok
@RestController
@RequestMapping("/clientes")
public class ClienteController {

   private final CadastroClienteService cadastroClienteService;
   
//   @Autowired - o jakarta cria a implementacao automatica
   private final ClienteRepository clienteRepository;
   
   
   @GetMapping()
   public List<Cliente> listar() {
      return clienteRepository.findByNomeContaining("Al");
//      return clienteRepository.findByNome("Danley Alves");
//      return clienteRepository.findAll();
   }
   
   
   @GetMapping("/{clienteId}")
   public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
      Optional<Cliente> cliente = clienteRepository.findById(clienteId);
      
      if (cliente.isPresent()){
         return ResponseEntity.ok(cliente.get());
      }
      
      return ResponseEntity.notFound().build();
   }
   
   @ResponseStatus(HttpStatus.CREATED)
   @PostMapping
   public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
      return cadastroClienteService.salvar(cliente);
   }
   
   
   @PutMapping("{clienteId}")
   public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
      if(clienteRepository.existsById(clienteId)) {
         return ResponseEntity.notFound().build();
      }
      
      cliente.setId(clienteId);
      cliente = cadastroClienteService.salvar(cliente);
      
      return ResponseEntity.ok(cliente);
   }
   
   
   @DeleteMapping("/{clienteId}")
   public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
      if(!clienteRepository.existsById(clienteId)) {
         return ResponseEntity.notFound().build();
      }
      
      cadastroClienteService.excluir(clienteId);
      
      return ResponseEntity.noContent().build();
   }
   
   
}
