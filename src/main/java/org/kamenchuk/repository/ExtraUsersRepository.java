package org.kamenchuk.repository;

import org.kamenchuk.models.ExtraUsersData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraUsersRepository extends JpaRepository<ExtraUsersData, Long> {

}
