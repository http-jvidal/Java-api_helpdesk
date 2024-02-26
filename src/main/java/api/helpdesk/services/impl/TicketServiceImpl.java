package api.helpdesk.services.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.Departament;
import api.helpdesk.domain.models.Ticket;
import api.helpdesk.domain.repository.DepartamentRepository;
import api.helpdesk.domain.repository.TicketRepository;
import api.helpdesk.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private final TicketRepository ticketRepository;
    private final DepartamentRepository departamentoRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, DepartamentRepository departamentoRepository) {
        this.ticketRepository = ticketRepository;
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        if(!ticketRepository.existsById(id)) 
            throw new IllegalArgumentException("Id not found");
        else {
            return ticketRepository.findById(id);       
        }
    }

    @Override
    public void createCalled(String nome, String detalhes, String contato, Departament departamentName) {
        
        Departament existingDepartament = departamentoRepository.findByName(departamentName.getName());
        
        if(existingDepartament != null){
            Ticket ticket = new Ticket(nome, detalhes, contato, existingDepartament);
            ticketRepository.save(ticket);
        } else {
            throw new IllegalArgumentException("Departamento incorreto ou não existe, faça cadastro");
        }
            
    }

    @Override
    public List<Ticket> findAll() {
            List<Ticket> res = ticketRepository.findAll();
            return res;
    }

    @Override
    public void delete(Long id) {
        if(!ticketRepository.existsById(id))
            throw new IllegalArgumentException("Ticket Id not exists");
        else 
            ticketRepository.deleteById(id);
        
    }

    @Override
    public Ticket update(Ticket ticket) {
        Optional<Ticket> ticketId = ticketRepository.findById(ticket.getId());
        if(!ticketId.isPresent())
            throw new IllegalArgumentException("Id not exists");
        else{
            final Ticket ticketUpdate = ticketRepository.save(ticket);
            return ticketUpdate;
        }   
    }


    
    
}
