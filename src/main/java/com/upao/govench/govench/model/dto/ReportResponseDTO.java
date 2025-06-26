package com.upao.govench.govench.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDTO {
    private String message;
    private List<EventBasicDTO> createdEvents;
}
