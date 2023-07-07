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
public class OrderCreateResponse {
    private LocalDate startDate;
    private LocalDate finishDate;
    private String carModel;
    private String carMark;
    private String userName;
    private String userLastname;
    private Integer price;
}
