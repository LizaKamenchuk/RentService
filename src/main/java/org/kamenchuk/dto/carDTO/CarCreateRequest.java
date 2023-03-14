package org.kamenchuk.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarCreateRequest {
    private String carNumber;
    private String mark;
    private String model;
    private String limitations;
    private Integer price;
    private Integer idImage;
}
