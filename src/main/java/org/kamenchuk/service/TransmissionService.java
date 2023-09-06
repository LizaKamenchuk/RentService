package org.kamenchuk.service;

import org.kamenchuk.models.Transmission;

public interface TransmissionService {
     String save (String transmissionType);
     void deleteById (Integer id);
     Transmission findByTransmissionType(String transmissionType);
}
