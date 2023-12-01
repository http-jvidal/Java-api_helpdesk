package api.helpdesk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.helpdesk.domain.models.Ticket;
import api.helpdesk.services.TicketService;

@RestController
@RequestMapping("api/ticket")
@CrossOrigin(origins =  {"http://localhost:4200"})
public class TicketController {
    
    @Autowired
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> findAll(){
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable Long id){
        Optional<Ticket> ticket = ticketService.findById(id);
        if(ticket.isPresent())
            return new ResponseEntity<Ticket>(ticket.get(), HttpStatus.OK);
        else 
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create")
    public ResponseEntity<Ticket> createCalled(@RequestBody Ticket ticket, Long id){
        Optional<Ticket> ticketId = ticketService.findById(id);
        if(ticketId.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            ticketService.createCalled(ticket);
            return ResponseEntity.noContent().build();
        }   
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Ticket ticket){
        Optional<Ticket> ticketId = ticketService.findById(id);
        if(ticketId.isPresent()){
            ticketService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Long id, @RequestBody Ticket ticket){
        Optional<Ticket> ticketId = ticketService.findById(id);
        Ticket res = ticketService.update(ticket);
        if(ticketId.isPresent()){
            return ResponseEntity.ok(res);
        } else {
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
        }
    }

}
