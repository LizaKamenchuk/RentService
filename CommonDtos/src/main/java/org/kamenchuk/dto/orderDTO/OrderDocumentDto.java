package org.kamenchuk.dto.orderDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kamenchuk.dto.carDTO.CarDocumentDto;
import org.kamenchuk.dto.userDTO.AdminDocumentDto;
import org.kamenchuk.dto.userDTO.UserDocumentDto;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDocumentDto {
    private LocalDate startDate;
    private LocalDate finishDate;
    private CarDocumentDto carDocumentDto;
    private AdminDocumentDto adminDocumentDto;
    private UserDocumentDto userDocumentDto;
}
