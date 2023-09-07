package org.kamenchuk.repository;

import org.kamenchuk.dto.carDTO.model_markDTO.MarkDto;
import org.kamenchuk.models.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
    boolean existsMarkByMark(MarkDto mark);

    Optional<Mark> findMarkByMark(MarkDto mark);
}
