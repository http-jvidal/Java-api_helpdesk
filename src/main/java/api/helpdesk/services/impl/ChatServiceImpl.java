package api.helpdesk.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.Chat;
import api.helpdesk.domain.repository.ChatRepository;
import api.helpdesk.services.ChatService;


@Service
public class ChatServiceImpl implements ChatService{

      private final ChatRepository chatRepository;

      public ChatServiceImpl(ChatRepository chatRepository){
            this.chatRepository = chatRepository;
      }

      @Override
      public Optional<Chat> findById(Long id) {
            if(!chatRepository.existsById(id)){
                  throw new IllegalArgumentException("Erro ao retornar ID " + id);
            } else {
                  return chatRepository.findById(id);
            }
      }

      @Override
      public List<Chat> findAll() {
            return chatRepository.findAll();
      }


}
