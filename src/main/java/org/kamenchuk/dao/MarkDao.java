package org.kamenchuk.dao;

import org.kamenchuk.models.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarkDao extends JpaRepository<Mark, Integer> {
    boolean existsMarkByMark(String mark);

    Optional<Mark> findMarkByMark(String mark);
}
