package org.kamenchuk.authModule.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.authModule.feignClient.AuthJwtClient;
import org.kamenchuk.authModule.feignClient.dto.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {AuthModuleController.class})
class AuthModuleControllerTest {
    @Autowired
    private AuthModuleController controller;

    @MockBean
    private AuthJwtClient jwtClient;

    @Test
    void authenticate() {
        LoginCredentials credentials = new LoginCredentials();
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.OK);
        when(jwtClient.createAuthenticationToken(credentials)).thenReturn(response);
        ResponseEntity<String> result = controller.authenticate(credentials);
        assertAll(()->{
            assertNotNull(result);
            assertEquals(result,response);
        });
    }
}