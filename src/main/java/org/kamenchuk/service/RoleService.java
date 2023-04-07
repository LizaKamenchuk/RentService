package org.kamenchuk.service;

import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.dto.roleDTO.RoleResponse;

public interface RoleService {
    /**
     * Deletes role by id
     * @param id - roles id
     */
    void delete(Integer id);

    /**
     * Creates role
     * @param role - name of role
     * @return RoleResponse
     */
    RoleResponse create(String role) throws CreationException;
}
