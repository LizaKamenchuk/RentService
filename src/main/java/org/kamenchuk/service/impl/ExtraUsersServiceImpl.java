package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.exceptions.UpdatingException;
import org.kamenchuk.repository.ExtraUsersRepository;
import org.kamenchuk.repository.UserRepository;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.mapper.ExtraUsersDataMapper;
import org.kamenchuk.models.ExtraUsersData;
import org.kamenchuk.models.User;
import org.kamenchuk.service.ExtraUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class implements ExtraUsersService interface
 * @author Liza Kamenchuk
 */
@Service
@Slf4j
public class ExtraUsersServiceImpl implements ExtraUsersService {
    private final ExtraUsersRepository extraUsersRepository;
    private final UserRepository userRepository;
    private final ExtraUsersDataMapper extraDataMapper;

    @Autowired
    ExtraUsersServiceImpl(ExtraUsersRepository extraUsersRepository,
                          UserRepository userRepository,
                          ExtraUsersDataMapper extraDataMapper) {
        this.extraUsersRepository = extraUsersRepository;
        this.userRepository = userRepository;
        this.extraDataMapper = extraDataMapper;

    }


    @Override
    @Transactional
    public ExtraUserDataUpdateRequest getExtraDataById(Long id) throws ResourceNotFoundException {
        return extraUsersRepository.findById(id)
                .map(extraDataMapper::toDto)
                .orElseThrow(() -> {
                    log.error("getExtraDataById(). Data isn`t found");
                    return new ResourceNotFoundException("Data is not found");
                });
    }

    @Override
    @Transactional
    public ExtraUserDataUpdateRequest updateExtraData(ExtraUserDataUpdateRequest request, Long idUser) throws UpdatingException {
        User u = userRepository.findById(idUser).orElseThrow(()->{
            throw new ResourceNotFoundException(String.format("User with %s is not found",idUser));
        });
        Long idED = u.getExtraUsersData().getId();
        return extraUsersRepository.findById(idED)
                .map(user -> setChangedData(request, user))
                .map(extraUsersRepository::save)
                .map(extraDataMapper::toDto)
                .orElseThrow(() -> {
                    log.error("updateExtraData(). Updates are not save");
                    return new UpdatingException("Updates are not save");
                });
    }


    private ExtraUsersData setChangedData(ExtraUserDataUpdateRequest request, ExtraUsersData extraUsersData) {
        return ExtraUsersData.builder()
                .id(extraUsersData.getId())
                .idPassport((request.getIdPassport() == null || request.getIdPassport().isEmpty()) ? extraUsersData.getIdPassport() : request.getIdPassport())
                .name((request.getName() == null || request.getName().isEmpty()) ? extraUsersData.getName() : request.getName())
                .lastname((request.getLastname() == null || request.getLastname().isEmpty()) ? extraUsersData.getLastname() : request.getLastname())
                .phone((request.getPhone() == null || request.getPhone().isEmpty()) ? extraUsersData.getPhone() : request.getPhone())
                .dateOfBirth(request.getDateOfBirth() == null
                        ? extraUsersData.getDateOfBirth() : request.getDateOfBirth())
                .drivingLicense((request.getDrivingLicense() == null || request.getDrivingLicense().isEmpty())
                        ? extraUsersData.getDrivingLicense() : request.getDrivingLicense())
                .registerDate(extraUsersData.getRegisterDate())
                .build();
    }
}
