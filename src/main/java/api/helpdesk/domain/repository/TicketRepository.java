package api.helpdesk.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.helpdesk.domain.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    @SuppressWarnings("null")
    boolean existsById(Long id);

    @SuppressWarnings("null")
    List<Ticket> findAll();

}