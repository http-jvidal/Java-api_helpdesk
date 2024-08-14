package api.helpdesk.services;

import java.util.*;

import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.Ticket;
@Service
public interface TicketService {
    
    Optional<Ticket> findById(Long id);

    void createCalled(Ticket ticket);

    List<Ticket> findAll();

    void delete(Long id);

    Ticket update(Ticket ticketDetails);
}
