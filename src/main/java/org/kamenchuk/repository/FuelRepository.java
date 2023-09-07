package org.kamenchuk.repository;

import org.jetbrains.annotations.NotNull;
import org.kamenchuk.dto.carDTO.extraDataCarDTO.FuelDto;
import org.kamenchuk.models.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Integer> {
    void deleteById(@NotNull Integer id);
    Optional<Fuel> findFuelByFuelType(@NotNull FuelDto fuelType);
}
