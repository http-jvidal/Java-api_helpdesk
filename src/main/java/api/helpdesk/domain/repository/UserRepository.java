package api.helpdesk.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.helpdesk.domain.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    boolean existsById(Long id);
    boolean existsByName(String name);
    boolean existsByUsername(String username);
    List<User> findAll();
    List<User> findByNameContainingIgnoreCase(String name);
    User findByName(String name);
    
    User findByUsername(String username);
    
    List<User> findByUsernameContainingIgnoreCase(String username);

} 
