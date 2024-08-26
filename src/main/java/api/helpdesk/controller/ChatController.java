package api.helpdesk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.helpdesk.domain.models.Chat;
import api.helpdesk.services.ChatService;

@CrossOrigin
@RequestMapping("/api/chat")
@RestController
public class ChatController {
      private final ChatService chatService;

      public ChatController(ChatService chatService) {
            this.chatService = chatService;
      }


      @GetMapping(value = "/{id}")
      public ResponseEntity<?> findMessagesFromUserId(@PathVariable Long id){
            if(id != null){
                  Optional<Chat> messageUser = this.chatService.findById(id);
                  return new ResponseEntity<>(messageUser, HttpStatus.OK);
            } else {
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao buscar mensagens, usuario nulo " );

            }
      }

      @GetMapping("/")
      public List<Chat> findAllChats(){
            List<Chat> chat = chatService.findAll();
            return chat;

            
      }
}
