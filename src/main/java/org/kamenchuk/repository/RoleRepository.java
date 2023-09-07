package org.kamenchuk.repository;

import org.jetbrains.annotations.NotNull;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findFirstByRole(@NotNull RoleResponse role);

    Role getRoleById(Integer id);
}
