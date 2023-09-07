package org.kamenchuk.service;

import org.kamenchuk.dto.carDTO.model_markDTO.ModelCreateDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.NotValidMethodParameters;
import org.kamenchuk.models.Model;

/**
 * Interface ModelService for work with Model
 *
 * @author Lisa Kamenchuk
 */
public interface ModelService {
    /**
     * save model
     *
     * @param model - ModelCreateDto
     * @return model
     * @throws NotValidMethodParameters
     */
    Model saveIfNotExists(ModelCreateDto model) throws NotValidMethodParameters, CreationException;
}
