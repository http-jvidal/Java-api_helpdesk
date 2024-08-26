package api.helpdesk.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.User;
import api.helpdesk.domain.models.dto.Login;
import api.helpdesk.domain.models.dto.Sessao;
import api.helpdesk.domain.models.dto.UserDTO;

@Service
public interface UserService {

    Optional<UserDTO> findById (Long id);
    
    void saveUser(User user);

    List<UserDTO> findAll();
    
    User findByName(String name);

    List<UserDTO> findByNameContainingIgnoreCase(String name);

    void delete(Long id);

    UserDTO update ( User userDetails);

    User findByUsername(String username);

    boolean isValidUser(String username, String password);

    Sessao Authenticate(Login login);
}