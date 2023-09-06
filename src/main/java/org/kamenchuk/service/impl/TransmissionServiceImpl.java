package org.kamenchuk.service.impl;

import org.kamenchuk.repository.TransmissionRepository;
import org.kamenchuk.models.Transmission;
import org.kamenchuk.service.TransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
        return transmissionRepository.findByTransmissionType(transmissionType);
    }
}
