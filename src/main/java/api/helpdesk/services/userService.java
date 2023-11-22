package api.helpdesk.services;

import java.util.List;

import api.helpdesk.domain.models.User;

public interface UserService {

    User findById (Long id);
    
    User createUser (User user);

    List<User> findAll();
    
}