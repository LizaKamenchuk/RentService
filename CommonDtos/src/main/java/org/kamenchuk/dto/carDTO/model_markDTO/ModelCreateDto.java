package org.kamenchuk.dto.carDTO.model_markDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelCreateDto {
    private String model;
    private String mark;
}
