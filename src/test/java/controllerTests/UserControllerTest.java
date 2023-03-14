package controllerTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.kamenchuk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService usersService;

}
