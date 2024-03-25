package com.danley.awpag.domain.service;


import com.danley.awpag.domain.exception.NegocioException;
import com.danley.awpag.domain.model.Cliente;
import com.danley.awpag.domain.model.Parcelamento;
import com.danley.awpag.domain.repository.ClienteRepository;
import com.danley.awpag.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {
   
   private final ParcelamentoRepository parcelamentoRepository;
   private final CadastroClienteService cadastroClienteService;
   
   @Transactional
   public Parcelamento cadastrar(Parcelamento novoParcelamento) {
      if(novoParcelamento.getId() != null) {
         throw new NegocioException("Parcelamento a ser criado nao deve possuir um codigo");
      }
      
      Cliente cliente = cadastroClienteService.buscar(novoParcelamento.getCliente().getId());
      
      novoParcelamento.setCliente(cliente);
      novoParcelamento.setDescricao(String.valueOf(OffsetDateTime.now()));
      
      return parcelamentoRepository.save(novoParcelamento);
   }
}
