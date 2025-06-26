package com.upao.govench.govench.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CollectionResponseDTO {

    private Integer id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userName;
}
