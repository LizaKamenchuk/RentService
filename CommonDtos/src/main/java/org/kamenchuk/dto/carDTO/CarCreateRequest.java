package org.kamenchuk.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

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
    private String limitations;
    @NotNull
    private Integer price;
}
