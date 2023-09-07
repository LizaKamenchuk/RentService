package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.Transmission;
import org.kamenchuk.repository.TransmissionRepository;
import org.kamenchuk.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransmissionServiceImpl implements TransmissionService {
    private final TransmissionRepository transmissionRepository;

    @Autowired
    public TransmissionServiceImpl(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    @Override
    public String save(String transmissionType) {
        return transmissionRepository.save(Transmission.builder()
                        .transmissionType(transmissionType)
                        .build())
                .getTransmissionType();
    }

    @Override
    public void deleteById(Integer id) {
        transmissionRepository.deleteById(id);
    }

    @Override
    public Transmission findByTransmissionType(String transmissionType) {
        return transmissionRepository.findByTransmissionType(transmissionType).orElseThrow(()->{
            log.info("Transmission type " + transmissionType + " does not exist");
            throw new ResourceNotFoundException("Transmission type " + transmissionType + " does not exist");
        });
    }
}
