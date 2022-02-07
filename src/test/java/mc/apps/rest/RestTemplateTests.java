package mc.apps.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mc.apps.rest.modele.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @Order(0)
    public void RestControllerGetAllTest() throws MalformedURLException {
        ResponseEntity<User[]> response =
                restTemplate.getForEntity(new URL("http://localhost:" + port + "/rest/users").toString(), User[].class);

        Assertions.assertNotNull(response.getBody());
//        System.out.println(response.getBody());
//        System.out.println("port = "+port);

        User[] result = response.getBody();
//        System.out.println(result[0].getId());
        Arrays.stream(result).forEach(System.out::println);

    }
    @Test
    @Order(1)
    public void RestControllerPostTest() throws MalformedURLException {
        ResponseEntity<User> response =
                restTemplate.postForEntity(
                        new URL("http://localhost:" + port + "/rest/users").toString(),
                        new User("Jason","Bourne")
                        , User.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);

        User result = response.getBody();
        System.out.println("user (created) = "+result);
    }
}
