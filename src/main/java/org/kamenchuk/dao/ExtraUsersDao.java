package org.kamenchuk.dao;

import org.kamenchuk.models.ExtraUsersData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraUsersDao extends JpaRepository<ExtraUsersData, Long> {


}
