package org.kamenchuk.dto.extraDataCarDTO;

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
