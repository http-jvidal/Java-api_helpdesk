package api.helpdesk.services;

import java.util.*;

import api.helpdesk.domain.models.Departament;
import api.helpdesk.domain.models.Ticket;

public interface TicketService {
    Optional<Ticket> findById(Long id);

    void createCalled(String nome, String detalhes, String imagem, Departament departamentName);

    List<Ticket> findAll();

    void delete(Long id);

    Ticket update(Ticket ticketDetails);
}
