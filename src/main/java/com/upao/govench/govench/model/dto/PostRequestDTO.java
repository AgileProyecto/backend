package com.upao.govench.govench.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDTO {

    @NotBlank(message = "La Descripcion no puede estar vacia")
    private String body;
}


