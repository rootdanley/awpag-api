package com.danley.awpag.api.assembler;

import com.danley.awpag.api.model.Input.ParcelamentoInput;
import com.danley.awpag.api.model.ParcelamentoModel;
import com.danley.awpag.domain.model.Parcelamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@AllArgsConstructor
@Component
public class ParcelamentoAssembler {
   
   private final ModelMapper modelMapper;
   
   
   public ParcelamentoModel toModel(Parcelamento parcelamento) {
      return modelMapper.map(parcelamento, ParcelamentoModel.class);
   }

   public List<ParcelamentoModel> toCollectionsModel(List<Parcelamento> parcelamentos) {
      return parcelamentos.stream()
          .map(this::toModel)
          .toList();
   }
   
   public Parcelamento toEntity(ParcelamentoInput parcelamentoInput) {
      return modelMapper.map(parcelamentoInput, Parcelamento.class);
   }
   
}
