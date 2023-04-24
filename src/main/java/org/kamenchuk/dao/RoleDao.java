package org.kamenchuk.dao;

import org.kamenchuk.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {

    Optional<Role> findFirstByRole(String role);

    Role getRoleById(Integer id);
}
