package org.kamenchuk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UForUserOrderDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Integer idCar;
}
