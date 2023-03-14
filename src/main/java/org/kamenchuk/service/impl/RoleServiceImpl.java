package org.kamenchuk.service.impl;

import org.kamenchuk.dao.RoleDao;
import org.kamenchuk.dto.mapper.RoleMapper;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.models.Role;
import org.kamenchuk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
    private final RoleMapper roleMapper;

    @Autowired
    RoleServiceImpl(RoleDao roleDao, RoleMapper roleMapper){
        this.roleDao= roleDao;
        this.roleMapper = roleMapper;
    }

    @Override
    public void delete(Integer id) {
        //TODO isPresent
        Role role = roleDao.findById(id).get();
        roleDao.delete(role);
    }

    @Override
    public RoleResponse create(String role) {
        Role r = new Role();
        r.setRole(role);
        r = roleDao.save(r);
        return roleMapper.toDtoResponse(r);
    }
}
