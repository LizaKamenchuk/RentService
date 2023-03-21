package org.kamenchuk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.kamenchuk.dao.ExtraUsersDao;
import org.kamenchuk.dao.UserDao;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.dto.mapper.ExtraUsersDataMapper;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.ExtraUsersData;
import org.kamenchuk.models.User;
import org.kamenchuk.service.ExtraUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ExtraUsersServiceImpl implements ExtraUsersService {
    private ExtraUsersDao extraUsersDao;
    private UserDao userDao;
    private ExtraUsersDataMapper extraDataMapper;

    @Autowired
    ExtraUsersServiceImpl(ExtraUsersDao extraUsersDao, ExtraUsersDataMapper extraDataMapper) {
        this.extraUsersDao = extraUsersDao;
        this.extraDataMapper = extraDataMapper;
    }


    @Override
    @Transactional
    public ExtraUserDataUpdateRequest getExtraDataById(Long id) throws ResourceNotFoundException {
        return extraUsersDao.findById(id)
                .map(extraDataMapper::toDto)
                .orElseThrow(() -> {
                    log.error("getExtraDataById(). Data isn`t found");
                    return new ResourceNotFoundException("Data is not found");
                });
    }

    @Override
    @Transactional
    public ExtraUserDataUpdateRequest updateExtraData(ExtraUserDataUpdateRequest request,Long idUser) {
        User u = userDao.findById(idUser).get();
        Long idED = u.getExtraUsersData().getId();
        return extraUsersDao.findById(idED)
                .map(user -> setChangedData(request, user))
                .map(extraUsersDao::save)
                .map(extraDataMapper::toDto)
                .orElseThrow(() -> {
                    log.error("updateExtraData(). Updates are not save");
                    return new RuntimeException("Updates are not save");
                });
    }


    private ExtraUsersData setChangedData(ExtraUserDataUpdateRequest request, ExtraUsersData extraUsersData) {
       return ExtraUsersData.builder()
               .id(extraUsersData.getId())
               .idPassport(request.getIdPassport().isEmpty() ? extraUsersData.getIdPassport() : request.getIdPassport())
               .name(request.getName().isEmpty() ? extraUsersData.getName() : request.getName())
               .lastname(request.getLastname().isEmpty() ? extraUsersData.getLastname() : request.getLastname())
               .phone(request.getPhone().isEmpty() ? extraUsersData.getPhone() : request.getPhone())
               .dateOfBirth(request.getDateOfBirth() == null
                       ? extraUsersData.getDateOfBirth() : request.getDateOfBirth())
               .drivingLicense(request.getDrivingLicense().isEmpty()
                       ? extraUsersData.getDrivingLicense() : request.getDrivingLicense())
               .build();
    }
}
