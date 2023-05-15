package org.kamenchuk.rentModule.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.dto.extraUsersDataDTO.ExtraUserDataUpdateRequest;
import org.kamenchuk.rentModule.feinClient.FeignExtraDataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ExtraDataController.class})
class ExtraDataControllerTest {
    @Autowired
    private ExtraDataController controller;

    @MockBean
    private FeignExtraDataClient feignExtraDataClient;

    @Test
    void getById() {
        Long id = 1L;
        ExtraUserDataUpdateRequest response = new ExtraUserDataUpdateRequest();
        when(feignExtraDataClient.getById(id)).thenReturn(response);
        ExtraUserDataUpdateRequest result = controller.getById(id);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }

    @Test
    void update() {
        ExtraUserDataUpdateRequest request = new ExtraUserDataUpdateRequest();
        Long idED = 1L;
        ExtraUserDataUpdateRequest response = new ExtraUserDataUpdateRequest();
        when(feignExtraDataClient.update(request,idED)).thenReturn(response);
        ExtraUserDataUpdateRequest result = controller.update(request,idED);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }
}