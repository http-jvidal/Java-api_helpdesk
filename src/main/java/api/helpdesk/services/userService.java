package api.helpdesk.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.User;
import api.helpdesk.domain.models.dto.Login;
import api.helpdesk.domain.models.dto.Sessao;

@Service
public interface UserService {

    Optional<User> findById (Long id);
    
    void saveUser(User user);

    List<User> findAll();
    
    User findByName(String name);

    List<User> findByNameContainingIgnoreCase(String name);

    void delete(Long id);

    User update ( User userDetails);

    User findByUsername(String username);

    void SaveUserWithEncrypt (User user);

    boolean isValidUser(String username, String password);

    Sessao Authenticate(Login login);
}