package api.helpdesk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import api.helpdesk.domain.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long>{
      Optional<Chat> findById(Long id);
      
} 
