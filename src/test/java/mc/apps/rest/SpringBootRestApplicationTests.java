package mc.apps.rest;

import mc.apps.rest.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringBootRestApplicationTests {

    @Mock
    UserRepository userRepository;


    @Test
    void contextLoads() {
        assertNotNull(userRepository);
    }

}
