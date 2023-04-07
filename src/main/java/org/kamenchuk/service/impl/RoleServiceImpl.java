package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dao.RoleDao;
import org.kamenchuk.dto.mapper.RoleMapper;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.models.Role;
import org.kamenchuk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TODO см CarServiceImpl
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
    public void delete(Integer id) {
        //TODO isPresent
        Role role = roleDao.findById(id).get();
        roleDao.delete(role);
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
