package org.kamenchuk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CUCarDto {
    private Integer id;
    private String carNumber;
    private Integer price;
    private String limitations;
    private Integer idImage;
    private Integer idModel;
}
