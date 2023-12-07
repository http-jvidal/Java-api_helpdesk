package api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import api.helpdesk.domain.models.User;
import api.helpdesk.dto.Login;

public interface UserService {

    Optional<User> findById (Long id);
    
    User createUser (User user);

    List<User> findAll();
    
    User findByName(String name);

    List<User> findByNameContainingIgnoreCase(String name);

    void delete(Long id);

    User update ( User userDetails);

    Optional<User> findByUsername(String Login);

    Optional<User> findByPassword(String password);

    Login login(Login login);
    
}