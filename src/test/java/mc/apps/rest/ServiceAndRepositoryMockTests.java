package mc.apps.rest;

import mc.apps.rest.modele.User;
import mc.apps.rest.repositories.UserRepository;
import mc.apps.rest.services.UserService;
import mc.apps.rest.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceAndRepositoryMockTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    List<User> items = Arrays.asList(new User("John","Doe"), new User("Jane","Doe"));
    @BeforeEach
    public void initRepositoryMockReturn(){
        when(userRepository.findAll()).thenReturn(items);
    }

    @DisplayName("UserService + UserRepository Test")
    @Test
    public void testService() throws Exception {
        assertEquals(userService.all(), items);
    }
}
