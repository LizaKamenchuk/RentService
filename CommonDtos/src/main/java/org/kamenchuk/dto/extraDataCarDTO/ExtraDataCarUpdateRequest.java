package org.kamenchuk.dto.extraDataCarDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtraDataCarUpdateRequest {
    private String limitations;
    private Integer manufacture_year;
    private Double fuel_consumption;
    private FuelDto fuelDto;
    private CarClassDto carClassDto;
    private TransmissionDto transmissionDto;
}
