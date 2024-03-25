package com.danley.awpag.api.model.Input;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdInput {
   
   @NotNull
   private Long Id;
}
