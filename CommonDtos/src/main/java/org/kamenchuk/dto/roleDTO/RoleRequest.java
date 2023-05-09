package org.kamenchuk.dto.roleDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO почему DTO в модели?
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {
    private Integer id;
}
