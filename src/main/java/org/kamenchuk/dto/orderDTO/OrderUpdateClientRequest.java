package org.kamenchuk.dto.orderDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderUpdateClientRequest {
    private Long id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Integer idCar;
}
