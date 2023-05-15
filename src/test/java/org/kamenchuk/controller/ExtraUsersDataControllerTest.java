package org.kamenchuk.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.exceptions.ResourceNotFoundException;
import org.kamenchuk.service.ExtraUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExtraUsersDataController.class})
class ExtraUsersDataControllerTest {
    @Autowired
    private ExtraUsersDataController controller;
    @MockBean
    private ExtraUsersService service;

    @Test
    void getById() throws ResourceNotFoundException {
        Long id = 2L;
        ExtraUserDataUpdateRequest response = new ExtraUserDataUpdateRequest();
        when(service.getExtraDataById(id)).thenReturn(response);
        ExtraUserDataUpdateRequest result = controller.getById(id);
        assertEquals(result,response);
    }

    @Test
    void update() {
        Long id = 2L;
        ExtraUserDataUpdateRequest request = new ExtraUserDataUpdateRequest();
        ExtraUserDataUpdateRequest response = new ExtraUserDataUpdateRequest();
        when(service.updateExtraData(request,id)).thenReturn(response);
        ExtraUserDataUpdateRequest result = controller.update(request,id);
        assertEquals(result,response);

    }
}