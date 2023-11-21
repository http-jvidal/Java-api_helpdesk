package api.helpdesk.domain.models;

import jakarta.persistence.*;

@Table(name = "Departament")
public class Departament {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Departament")
    private String departament;
}
