package org.kamenchuk.dto.carDTO.extraDataCarDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransmissionDto {
    private String transmissionType;
}