package org.kamenchuk.repository;

import org.jetbrains.annotations.NotNull;
import org.kamenchuk.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> getCarByCarNumber(@NotNull String carNumber);
    void deleteCarByCarNumber(@NotNull String carNumber);

    Optional<Car> findById(@NotNull Integer integer);

}
