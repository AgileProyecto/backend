package com.upao.govench.govench.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingEventRequestDTO {

    @NotNull(message = "La puntuacion no puede estar vacia")
    @Pattern(regexp = "^[1-5]$", message = "La puntucion solo puede ser entre 0 y 5")
    private int valorPuntuacion;
}
