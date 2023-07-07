package org.kamenchuk.dto.orderDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderUpdateAdminRequest {
    private Long id;
    private String refuseReason;
    private Boolean status;
}
