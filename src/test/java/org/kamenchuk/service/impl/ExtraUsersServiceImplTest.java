package org.kamenchuk.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.repository.ExtraUsersRepository;
import org.kamenchuk.repository.UserRepository;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.mapper.ExtraUsersDataMapper;
import org.kamenchuk.models.ExtraUsersData;
import org.kamenchuk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ExtraUsersServiceImpl.class})
class ExtraUsersServiceImplTest {
    @Autowired
    ExtraUsersServiceImpl extraUsersService;

    @MockBean
    private ExtraUsersRepository extraUsersRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ExtraUsersDataMapper extraDataMapper;

    @Test
    void getExtraDataById() throws ResourceNotFoundException {
        Long id = 1L;
        ExtraUsersData extraUsersData = new ExtraUsersData();
        ExtraUserDataUpdateRequest response = new ExtraUserDataUpdateRequest();
        when(extraUsersRepository.findById(id)).thenReturn(Optional.of(extraUsersData));
        when(extraDataMapper.toDto(extraUsersData)).thenReturn(response);
        ExtraUserDataUpdateRequest result = extraUsersService.getExtraDataById(id);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void updateExtraData() {
        Long idUser = 1L;
        Long idED = 1L;
        LocalDate start = LocalDate.of(2023, 8, 23);
        LocalDate finish = LocalDate.of(2023, 8, 30);
        ExtraUserDataUpdateRequest request = new ExtraUserDataUpdateRequest();
        ExtraUsersData extraUsersData = ExtraUsersData.builder()
                .id(idED)
                .idPassport("454acacsc")
                .name("name")
                .lastname("lastname")
                .phone("626456223")
                .drivingLicense("644cssc")
                .dateOfBirth(start)
                .registerDate(finish)
                .build();
        User user = User.builder()
                .id(idUser)
                .extraUsersData(extraUsersData)
                .build();
        ExtraUserDataUpdateRequest response = ExtraUserDataUpdateRequest.builder()
                .idPassport(extraUsersData.getIdPassport())
                .phone(extraUsersData.getPhone())
                .drivingLicense(extraUsersData.getDrivingLicense())
                .name(extraUsersData.getName())
                .lastname(extraUsersData.getLastname())
                .dateOfBirth(extraUsersData.getDateOfBirth())
                .build();
        when(userRepository.findById(idUser)).thenReturn(Optional.ofNullable(user));
        when(extraUsersRepository.findById(idED)).thenReturn(Optional.of(extraUsersData));
        when(extraUsersRepository.save(extraUsersData)).thenReturn(extraUsersData);
        when(extraDataMapper.toDto(extraUsersData)).thenReturn(response);
        ExtraUserDataUpdateRequest result = extraUsersService.updateExtraData(request,idUser);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }
}