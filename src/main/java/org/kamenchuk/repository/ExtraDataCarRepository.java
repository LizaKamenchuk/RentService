package org.kamenchuk.repository;

import org.jetbrains.annotations.NotNull;
import org.kamenchuk.models.ExtraDataCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExtraDataCarRepository extends JpaRepository<ExtraDataCar, Integer> {
    void deleteById(@NotNull Integer id);
    Optional<ExtraDataCar> findById(@NotNull Integer id);
}
