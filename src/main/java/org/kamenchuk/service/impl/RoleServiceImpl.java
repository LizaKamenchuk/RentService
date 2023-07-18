package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dao.RoleDao;
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

/**
 * Class implements RoleService interface
 *
 * @author Liza Kamenchuk
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
    private final RoleMapper roleMapper;

    @Autowired
    RoleServiceImpl(RoleDao roleDao, RoleMapper roleMapper){
        this.roleDao= roleDao;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<String> delete(Integer id) throws ResourceNotFoundException {
//        Role role = roleDao.findById(id).get();
//        roleDao.delete(role);
        if(roleDao.findById(id).isPresent()){
            roleDao.delete(roleDao.findById(id).get());
            return new ResponseEntity<>("Successful deleted",HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("Role does not found");
        }
    }

    @Override
    @Transactional
    public RoleResponse create(String role) throws CreationException {
        try {
            Role r = new Role();
            r.setRole(role);
            r = roleDao.save(r);
            return roleMapper.toDtoResponse(r);
        }catch (Exception e){
            log.error("create(). Role isn`t created");
            throw new CreationException("Role isn`t created");
        }
    }
}
