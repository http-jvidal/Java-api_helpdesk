package api.helpdesk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.helpdesk.domain.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long>{
      
} 
