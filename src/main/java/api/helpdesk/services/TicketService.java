package api.helpdesk.services;

import java.util.*;


import api.helpdesk.domain.models.Ticket;

public interface TicketService {
    Optional<Ticket> findById(Long id);

    void createCalled(Ticket chamados);

    List<Ticket> findAll();

    void delete(Long id);

    Ticket update(Ticket ticketDetails);
}
