package org.kamenchuk.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDocumentDto {
    private String carNumber;
    private String mark;
    private String model;
    private Integer price;
    private String limitations;
    private Integer manufacture_year;
    private Double fuel_consumption;
    private String fuelType;
    private String carClassType;
    private String transmissionType;
}
