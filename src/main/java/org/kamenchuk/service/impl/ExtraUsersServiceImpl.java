package org.kamenchuk.service.impl;

import org.kamenchuk.dao.ExtraUsersDao;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUsersDataCreateRequest;
import org.kamenchuk.dto.mapper.ExtraUsersDataMapper;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.models.ExtraUsersData;
import org.kamenchuk.service.ExtraUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraUsersServiceImpl implements ExtraUsersService {
    private ExtraUsersDao extraUsersDao;
    private ExtraUsersDataMapper extraDataMapper;

    @Autowired
    ExtraUsersServiceImpl(ExtraUsersDao extraUsersDao, ExtraUsersDataMapper extraDataMapper) {
        this.extraUsersDao = extraUsersDao;
        this.extraDataMapper = extraDataMapper;
    }

    @Override
    public ExtraUserDataUpdateRequest getExtraDataById(Long id) {
        return extraUsersDao.findById(id)
                .map(extraDataMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found"));
    }

    @Override
    public ExtraUserDataUpdateRequest updateExtraData(ExtraUserDataUpdateRequest request) {
        return extraUsersDao.findById(request.getId())
                .map(user -> setChangedData(request, user))
                .map(extraUsersDao::save)
                .map(extraDataMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Updates are not save, try again"));
    }

    @Override
    public ExtraUsersData createExtra(ExtraUsersDataCreateRequest create) {
        return extraUsersDao.save(extraDataMapper.toUser(create));

    }

    private ExtraUsersData setChangedData(ExtraUserDataUpdateRequest request, ExtraUsersData extraUsersData) {
        if (request.getName() != null) extraUsersData.setName(request.getName());
        if (request.getLastname() != null) extraUsersData.setLastname(request.getLastname());
        if (request.getIdPassport() != null) extraUsersData.setIdPassport(request.getIdPassport());
        if (request.getDrivingLicense() != null) extraUsersData.setDrivingLicense(request.getDrivingLicense());
        if (request.getPhone() != null) extraUsersData.setPhone(request.getPhone());
        return extraUsersData;
    }
}
