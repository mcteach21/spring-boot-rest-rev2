package mc.apps.rest.controllers;

import mc.apps.rest.repositories.User;
import mc.apps.rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DefaultController {

    // Inject repository
    final UserRepository userRepository;
    public DefaultController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/rest/users")
    public ResponseEntity<List<User>> test() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/rest/users/name/{name}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(userRepository.findByLastName(name), HttpStatus.OK);
    }

    @GetMapping("/rest/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> tutorialData = userRepository.findById(id);
        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/rest/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            User newUser = userRepository.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/rest/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {

            Optional<User> currentUser = userRepository.findById(id);
            if(currentUser.isPresent()) {

                User _user = currentUser.get();
                _user.setFirstName(user.getFirstName());
                _user.setLastName(user.getLastName());

                return new ResponseEntity<>(userRepository.save(_user) , HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
    }
    @DeleteMapping("/rest/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/rest/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
