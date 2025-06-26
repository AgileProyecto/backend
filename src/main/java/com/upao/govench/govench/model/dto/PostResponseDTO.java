package com.upao.govench.govench.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    private int id;
    private String body;
    private UserBasicDTO autor;
    private int comunidadId; // Agregar el ID de la comunidad
    private LocalDate created;
    private LocalDateTime updated;

}

