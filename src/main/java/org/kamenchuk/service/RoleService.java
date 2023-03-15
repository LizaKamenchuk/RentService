package org.kamenchuk.service;

import org.kamenchuk.models.roleDTO.RoleResponse;

public interface RoleService {
    void delete(Integer id);

    RoleResponse create(String role);
}
