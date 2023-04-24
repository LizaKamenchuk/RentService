package com.kamenchuk.authefication.repository;

import com.kamenchuk.authefication.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role getRoleById(Integer id);
}
