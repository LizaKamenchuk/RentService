package org.kamenchuk.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.kamenchuk.dto.extraDataCarDTO.ExtraDataCarCreateRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarCreateRequest {
    @NotNull
    private String carNumber;
    @NotNull
    private String mark;
    @NotNull
    private String model;
    @NotNull
    private Integer price;

    @NotNull
    private ExtraDataCarCreateRequest extraDataCarCreateRequest;

    @NotNull
    private String fuelType;
    @NotNull
    private String carClassType;
    @NotNull
    private String transmissionType;
}
