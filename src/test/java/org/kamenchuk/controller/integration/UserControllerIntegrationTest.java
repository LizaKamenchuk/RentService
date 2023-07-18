package org.kamenchuk.controller.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.kamenchuk.controller.RoleController;
import org.kamenchuk.dto.roleDTO.RoleResponse;
import org.kamenchuk.dto.userDTO.UserCreateRequest;
import org.kamenchuk.dto.userDTO.UserResponse;
import org.kamenchuk.exceptions.CreationException;
import org.kamenchuk.service.impl.integration.PostgresContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIntegrationTest extends PostgresContainer {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private RoleController roleController;
    private final static String REQUEST_MAPPING = "/rent_module/user";
    @LocalServerPort
    private int port;


    @Order(2)
    @Test
    void getAllUser() {
        List<UserResponse> responses = new ArrayList<>();
        responses.add(getUserResponse());
        ParameterizedTypeReference<List<UserResponse>> paramType = new ParameterizedTypeReference<List<UserResponse>>() {
        };
        ResponseEntity<List<UserResponse>> results = restTemplate.exchange(REQUEST_MAPPING + "/admin/all", HttpMethod.GET, null, paramType);
        assertAll(() -> {
            assertNotNull(results);
            assertEquals(results.getBody(), responses);
            assertEquals(results.getBody().size(), 1);
        });
    }

    @Order(3)
    @Test
    void findById() {
        Long id = 1L;
        UserResponse response = getUserResponse();
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(REQUEST_MAPPING + "/admin/findById/{id}", UserResponse.class, id);
        assertAll(() -> {
            assertEquals(result.getStatusCode(), HttpStatus.OK);
            assertEquals(result.getBody(), response);
        });
    }

    @Order(1)
    @Test
    void create() throws CreationException {
        roleController.createRole("USER");
        UserCreateRequest request = getUserCreateRequest();
        UserResponse response = getUserResponse();
        ResponseEntity<UserResponse> result = restTemplate.postForEntity(REQUEST_MAPPING + "/create", request, UserResponse.class);
        assertAll(() -> {
            assertEquals(result.getStatusCode(), HttpStatus.OK);
            assertEquals(result.getBody(), response);
        });
    }

    @Test
    void delete() {

    }

    @Order(4)
    @Test
    void updateLogin() {
        HttpHeaders headers = new HttpHeaders();
        String newLogin = "newLogin";
        Long id = 1L;
        UserResponse response = getUserResponse();
        response.setLogin(newLogin);
//       UserResponse result = restTemplate.postForObject(REQUEST_MAPPING + "/updateLogin/{id}", newLogin, UserResponse.class, id);
//        restTemplate.put(REQUEST_MAPPING + "/updateLogin/{id}",newLogin, id);

        UserResponse result = WebClient.create("http://localhost:"+port)
                .post()
                .uri(uriBuilder -> uriBuilder.path(REQUEST_MAPPING+"/updateLogin/{id}")
                                .queryParam("newLogin","newLogin")
                                .build(id))
                .retrieve()
                .bodyToMono(UserResponse.class).block();
        assertAll(() -> {
            assertEquals(result,response);
        });
    }

    private static UserCreateRequest getUserCreateRequest() {
        return UserCreateRequest.builder()
                .login("login")
                .password("password")
                .name("name")
                .lastname("lastname")
                .build();
    }

    private static UserResponse getUserResponse() {
        return UserResponse.builder()
                .id(1L)
                .login("login")
                .roleResponse(new RoleResponse("USER"))
                .idED(1L)
                .build();
    }
}