package org.kamenchuk.repository;

import org.jetbrains.annotations.NotNull;
import org.kamenchuk.models.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarClassRepository extends JpaRepository<CarClass, Integer> {
    void deleteById(@NotNull Integer integer);

    Optional<CarClass> findByClassType(@NotNull String carClassType);
}
