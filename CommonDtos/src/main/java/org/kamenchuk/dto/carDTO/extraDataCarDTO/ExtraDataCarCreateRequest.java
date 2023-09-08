package org.kamenchuk.dto.carDTO.extraDataCarDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtraDataCarCreateRequest {
    private String limitations;
    @NotNull
    private Integer manufacture_year;
    @NotNull
    private Double fuel_consumption;
}
