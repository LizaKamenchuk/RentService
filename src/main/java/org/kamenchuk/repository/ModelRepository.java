package org.kamenchuk.repository;

import org.jetbrains.annotations.NotNull;
import org.kamenchuk.dto.carDTO.model_markDTO.ModelCreateDto;
import org.kamenchuk.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
 boolean existsModelByModelAndMark_Mark(@NotNull ModelCreateDto dto);

 Optional<Model> findModelByModelAndMark_Mark(@NotNull ModelCreateDto dto);
}
