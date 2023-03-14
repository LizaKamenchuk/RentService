package org.kamenchuk.dao;

import org.kamenchuk.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelDao extends JpaRepository<Model, Integer> {
 boolean existsModelByModelAndMark_Mark(String model, String mark);

 Optional<Model> findModelByModelAndMark_Mark(String model,String mark);
}
