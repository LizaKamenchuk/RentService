package org.kamenchuk.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarResponse {
    private Integer id;
    private String carNumber;
    private String mark;
    private String model;
    private Integer price;
    private List<PhotoResponse> photos;
}