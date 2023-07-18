package org.kamenchuk.service;

import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

/**
 * Interface RoleService for work with Role
 * @author Lisa Kamechuk
 */
public interface RoleService {
    /**
     * Deletes role by id
     * @param id - roles id
     */
    ResponseEntity<String> delete(Integer id) throws ResourceNotFoundException;

    /**
     * Creates role
     * @param role - name of role
     * @return RoleResponse
     */
    RoleResponse create(String role) throws CreationException;
}
