package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.carDTO.model_markDTO.MarkDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.mapper.MarkMapper;
import org.kamenchuk.models.Mark;
import org.kamenchuk.repository.MarkRepository;
import org.kamenchuk.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    private final MarkMapper markMapper;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository,
                           MarkMapper markMapper) {
        this.markRepository = markRepository;
        this.markMapper = markMapper;
    }

    @Override
    public boolean existsMarkByMark(String mark) {
        return markRepository.existsMarkByMark(mark);
    }

    @Override
    public Mark findMarkByMark(String mark) throws ResourceNotFoundException {
        return markRepository.findMarkByMark(mark).orElseThrow(() -> {
            throw new ResourceNotFoundException(String.format("Mark %s does not exist", mark));
        });
    }

    @Override
    public Mark save(MarkDto mark) throws CreationException {
        return Optional.ofNullable(mark)
                .map(markMapper::toModel)
                .map(markRepository::save)
                .orElseThrow(() -> {
                    log.info(String.format("Mark %s is not created", mark));
                    throw new CreationException(String.format("Mark %s is not created", mark));
                });


    }
}
