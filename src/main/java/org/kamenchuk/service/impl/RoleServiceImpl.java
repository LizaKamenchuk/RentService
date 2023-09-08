package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.repository.RoleRepository;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.mapper.RoleMapper;
import org.kamenchuk.models.Role;
import org.kamenchuk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Class implements RoleService interface
 *
 * @author Liza Kamenchuk
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<String> delete(Integer id) throws ResourceNotFoundException {
        if (roleRepository.findById(id).isPresent()) {
            roleRepository.delete(roleRepository.findById(id).get());
            return new ResponseEntity<>("Successful deleted", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Role does not found");
        }
    }

    @Override
    @Transactional
    public RoleResponse create(RoleResponse role) throws CreationException {
        return Optional.ofNullable(role)
                .map(roleMapper::toRoleFromResponse)
                .map(roleRepository::save)
                .map(roleMapper::toDtoResponse)
                .orElseThrow(() -> {
                    log.error("create(). Role isn`t created");
                    throw new CreationException("Role isn`t created");
                });
    }

    @Override
    public Role getRoleByRole(String role) throws ResourceNotFoundException {
        return roleRepository.findFirstByRole(role)
          .orElseThrow(() -> {
            throw new ResourceNotFoundException(String.format("Role %s does not exist", role));
        });

    }
}
