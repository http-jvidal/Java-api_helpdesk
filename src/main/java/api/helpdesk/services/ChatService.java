package api.helpdesk.services;

import java.util.List;
import java.util.Optional;
import api.helpdesk.domain.models.Chat;

public interface ChatService {

      Optional<Chat> findById(Long id);

      List<Chat> findAll();


      void saveChat(Chat chat);
} 
