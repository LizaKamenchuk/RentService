package org.kamenchuk.service.impl;

import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.Mark;
import org.kamenchuk.repository.MarkRepository;
import org.kamenchuk.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
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
    public Mark save(String mark) {
        return markRepository.save(Mark.builder().mark(mark).build());
    }
}
