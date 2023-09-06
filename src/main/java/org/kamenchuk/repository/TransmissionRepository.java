package org.kamenchuk.repository;

import org.jetbrains.annotations.NotNull;
import org.kamenchuk.models.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransmissionRepository extends JpaRepository<Transmission, Integer> {
    void deleteById(@NotNull Integer integer);

    Optional<Transmission> findByTransmissionType(String transmissionType);
}
