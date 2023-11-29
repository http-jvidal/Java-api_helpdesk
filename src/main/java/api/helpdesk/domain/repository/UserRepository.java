package api.helpdesk.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.helpdesk.domain.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String name);
    List<User> findAll();
    User findByName(String name);
    List<User> findByNameContainingIgnoreCase(String name);
} 
