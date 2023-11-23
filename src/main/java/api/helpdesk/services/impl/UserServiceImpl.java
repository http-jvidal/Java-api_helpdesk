package api.helpdesk.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.User;
import api.helpdesk.domain.repository.UserRepository;
import api.helpdesk.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;
    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
            Optional<User> user  = userRepository.findById(id);
            return user;
    }

    public void createUser(User user){
        String pass = user.getPassword();
        user.setPassword(encoder.encode(pass));
        userRepository.save(user);
    }
    
    public List<User> findAll(){
        List<User> res = userRepository.findAll();
            return res;
    }

    @Override
    public User findByName(String name) {
        User res = userRepository.findByName(name);
            return res;
    }

    @Override
    public List<User> findByNameContainingIgnoreCase(String name) {
        List<User> res = userRepository.findByNameContainingIgnoreCase(name);
            return res;
    }

}
