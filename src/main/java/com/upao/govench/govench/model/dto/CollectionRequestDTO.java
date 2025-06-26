package com.upao.govench.govench.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionRequestDTO {

    @NotBlank(message = "El nombre de la colecion no puede estar vacio")
    private String name;
}
