package com.danley.awpag.domain.service;


import com.danley.awpag.domain.exception.NegocioException;
import com.danley.awpag.domain.model.Cliente;
import com.danley.awpag.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class CadastroClienteService {
   private final ClienteRepository clienteRepository;
   
   
   public Cliente buscar(Long clienteId) {
      return clienteRepository.findById(clienteId)
          .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
   }
   
   @Transactional
   public Cliente salvar(Cliente cliente) {
      boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
          .isPresent();
      if (emailEmUso) {
         throw new NegocioException("Já existe um cliente cadastro com este e-mail");
      }
      return clienteRepository.save(cliente);
   }
   
   @Transactional
   public void excluir(Long clienteId) {
      clienteRepository.deleteById(clienteId);
   }
}
