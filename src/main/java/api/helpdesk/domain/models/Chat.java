package api.helpdesk.domain.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Chat")
public class Chat {
      @Id
      @GeneratedValue (strategy = GenerationType.IDENTITY )
      private Long id;

      @ManyToOne(fetch = FetchType.EAGER)
      @JoinColumn(name = "user_id")
      private User user;

      @ManyToOne(fetch = FetchType.EAGER)
      @JoinColumn(name = "ticket_id")
      private Ticket ticket;
      
      private String mensagem;

      public Chat(){
            
      }
      
      public Chat(User user, Ticket ticket, String mensagem) {
            this.user = user;
            this.ticket = ticket;
            this.mensagem = mensagem;
      }

      public Ticket getTicket() {
            return ticket;
      }

      public void setTicket(Ticket ticket) {
            this.ticket = ticket;
      }


      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public User getUser() {
            return user;
      }

      public void setUser(User user) {
            this.user = user;
      }

      public String getMensagem() {
            return mensagem;
      }

      public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
      }

}
