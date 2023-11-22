package api.helpdesk.services.impl;

import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.User;
import api.helpdesk.domain.repository.UserRepository;
import api.helpdesk.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public User createUser(User user){
        if(userRepository.existsById(user.getId()))
            throw new IllegalArgumentException("This user already exists");
        else 
            return userRepository.save(user);
    }
    
    public List<User> findAll(){
        List<User> res = userRepository.findAll();
            return res;
    }
    
    
}
