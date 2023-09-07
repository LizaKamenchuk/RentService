package org.kamenchuk.service;

import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.Mark;

public interface MarkService {

    boolean existsMarkByMark(String mark);

    Mark findMarkByMark(String mark) throws ResourceNotFoundException;

    Mark save(String mark);

}
