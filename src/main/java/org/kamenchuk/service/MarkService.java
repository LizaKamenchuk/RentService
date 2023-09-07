package org.kamenchuk.service;

import org.kamenchuk.dto.carDTO.model_markDTO.MarkDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.Mark;

/**
 * Interface MarkService for work with Mark
 *
 * @author Lisa Kamenchuk
 */
public interface MarkService {
    /**
     * check if this mark exists
     *
     * @param mark - mark name
     * @return boolean
     */
    boolean existsMarkByMark(MarkDto mark);

    /**
     * get mark by mark name
     *
     * @param mark - mark name
     * @return mark
     * @throws ResourceNotFoundException
     */
    Mark findMarkByMark(MarkDto mark) throws ResourceNotFoundException;

    /**
     * save mark
     *
     * @param mark - mark name
     * @return mark
     */
    Mark save(MarkDto mark) throws CreationException;

}
