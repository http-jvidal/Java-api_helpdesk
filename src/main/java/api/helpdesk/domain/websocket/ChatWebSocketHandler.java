package api.helpdesk.domain.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import api.helpdesk.domain.models.User;
import api.helpdesk.domain.models.dto.UserDTO;
import api.helpdesk.services.ChatService;
import api.helpdesk.services.TicketService;
import api.helpdesk.services.UserService;

public class ChatWebSocketHandler extends TextWebSocketHandler{
    
    private ChatService chatService;

    private UserService userService;

    private TicketService ticketService;
    

    private List<WebSocketSession> sessions = new ArrayList<>();
    
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        sessions.add(session);
    }

    protected void handlerTextMessage(WebSocketSession session, TextMessage message, UserDTO users) throws Exception{
        String paylod = message.getPayload();


        User user = userService.findByUsername(users.getUsername());

        for(WebSocketSession sess : sessions) {
            if(sess.isOpen()){
                sess.sendMessage(message);
            }
        }
    }

    public void afterConnectionClosed(WebSocketSession session) throws Exception {
        sessions.remove(session);   
    }

}
