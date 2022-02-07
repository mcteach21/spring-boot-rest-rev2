package mc.apps.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import mc.apps.rest.repositories.User;
import mc.apps.rest.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @RunWith(SpringRunner.class) (junit 4) replaced by  @ExtendWith (more powerful)!

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // ==> for MockMvc !
class SpringBootRestWithMockMvcTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/rest/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty() )
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1) );
    }
    @Test
    public void testGetById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rest/users/2");
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]").isNotEmpty());
    }
    @Test
    public void testPostNewUser() throws Exception {
        User user = new User("Mehdi", "Chou.");
        mvc.perform(
                MockMvcRequestBuilders.post("/rest/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user))
        ).andExpect(status().isCreated());
    }



    private String asJsonString(Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);

            System.out.println(jsonContent);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
