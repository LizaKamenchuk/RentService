package org.kamenchuk.dao;

import org.kamenchuk.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {
    Optional<Car> getCarByCarNumber(String carNumber);
    void deleteCarByCarNumber(String carNumber);
    @Override
    Optional<Car> findById(Integer integer);
}
