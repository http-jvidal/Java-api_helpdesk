package api.helpdesk.services.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.User;
import api.helpdesk.domain.repository.UserRepository;
import api.helpdesk.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id){
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("Id Not Found");
        } else {
            Optional<User> user  = userRepository.findById(id);
            return user;
        }
    }

    public void createUser(User user){
        if(user.getUsername().isEmpty() ||user.getPassword().isEmpty())
            throw new IllegalArgumentException("Login and password cannot be null");
        if(userRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException("User Already exists");
        userRepository.save(user);
    }

    public List<User> findAll(){
        List<User> res = userRepository.findAll();
        return res;
        
    }

    @Override
    public User findByName(String name) {
        if(!userRepository.existsByUsername(name)){
            throw new IllegalArgumentException("This username not exists");
        } else {
            User res = userRepository.findByName(name);
            return res;
        }
        
    }

    @Override
    public List<User> findByNameContainingIgnoreCase(String name) {
        if(!userRepository.existsByName(name)){
            throw new IllegalArgumentException("This user not exists");
        } else {
            List<User> res = userRepository.findByNameContainingIgnoreCase(name);
            return res;
        }
        
    }

    @Override
    public void delete(Long id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("This Id not exists");
        } else {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User update(User user) {
        Optional<User> userId  = userRepository.findById(user.getId());
        if(!userId.isPresent()){
            throw new IllegalArgumentException("User not exists");
        } else {
            final User userUpdate = userRepository.save(user);
            return userUpdate;
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User login(User user) {
        var userExists = userRepository.findByUsername(user.getUsername());
        if(userExists == null)
            throw new IllegalArgumentException("User not exists in database");
        return user;

    }

    
}
