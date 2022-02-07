package mc.apps.rest;

import mc.apps.rest.modele.User;
import mc.apps.rest.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Stream;

@SpringBootApplication
@Controller
public class SpringBootRestApplication {
    private static final Logger logger = LogManager.getLogger(SpringBootRestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApplication.class, args);
    }

    private String DEFAUlT_VIEW="index";
    @GetMapping("/{page}")
    public String display(@PathVariable String page){
        return "".equals(page)?DEFAUlT_VIEW:page;
    }

    /**
     * init database (h2)
     */
    @Bean
    CommandLineRunner init(UserRepository userRepository){
        return args -> {
            Stream.of("Homer", "Marge", "Bart", "Lisa").forEach(name->{
                userRepository.save(new User(name, "Simpson"));
            });

            userRepository.save(new User("John", "Doe"));
            userRepository.save(new User("Jane", "Doe"));

            userRepository.findAll().forEach(logger::info);
        };
    }
}
