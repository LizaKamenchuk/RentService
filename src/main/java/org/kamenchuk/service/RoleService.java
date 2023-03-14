package org.kamenchuk.service;

import org.kamenchuk.dto.roleDTO.RoleResponse;

public interface RoleService {
    void delete(Integer id);

    RoleResponse create(String role);
}
