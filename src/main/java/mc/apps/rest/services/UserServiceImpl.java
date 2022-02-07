package mc.apps.rest.services;

import mc.apps.rest.modele.User;
import mc.apps.rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> all() {
        System.out.println("userRepository (inject) : "+(this.userRepository!=null));
        return userRepository.findAll();
    }
}
