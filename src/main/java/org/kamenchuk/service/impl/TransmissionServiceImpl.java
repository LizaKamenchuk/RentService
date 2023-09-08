package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dto.carDTO.extraDataCarDTO.TransmissionDto;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.mapper.TransmissionMapper;
import org.kamenchuk.models.Transmission;
import org.kamenchuk.repository.TransmissionRepository;
import org.kamenchuk.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TransmissionServiceImpl implements TransmissionService {
    private final TransmissionRepository transmissionRepository;
    private final TransmissionMapper transmissionMapper;

    @Autowired
    public TransmissionServiceImpl(TransmissionRepository transmissionRepository,
                                   TransmissionMapper transmissionMapper) {
        this.transmissionRepository = transmissionRepository;
        this.transmissionMapper = transmissionMapper;
    }

    @Override
    public TransmissionDto save(TransmissionDto transmissionDto) {
        return Optional.ofNullable(transmissionDto)
                .map(transmissionMapper::toModel)
                .map(transmissionRepository::save)
                .map(transmissionMapper::toDto)
                .orElseThrow(() -> {
                    throw new CreationException("Transmission is mot created");
                });
    }

    @Override
    public void deleteById(Integer id) {
        transmissionRepository.deleteById(id);
    }

    public Transmission findByTransmissionTypeOrFail(String transmissionType) {
        return transmissionRepository.findByTransmissionType(transmissionType).orElseThrow(() -> {
            log.info("Transmission type " + transmissionType + " does not exist");
            throw new ResourceNotFoundException("Transmission type " + transmissionType + " does not exist");
        });
    }

    @Override
    public Transmission findByTransmissionType(String transmissionType) {
        return transmissionRepository.findByTransmissionType(transmissionType).orElse(null);
    }
}
