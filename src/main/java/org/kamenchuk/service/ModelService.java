package org.kamenchuk.service;

import org.kamenchuk.dto.carDTO.model_markDTO.ModelCreateDto;
import org.kamenchuk.exceptions.NotValidMethodParameters;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.Model;

public interface ModelService {
    boolean existsModelByModelAndMark_Mark(String model, String mark);

    Model findModelByModelAndMark_Mark(String model, String mark) throws ResourceNotFoundException;

    Model saveIfNotExists(ModelCreateDto model) throws NotValidMethodParameters;
}
