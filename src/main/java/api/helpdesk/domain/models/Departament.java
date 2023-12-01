package api.helpdesk.domain.models;

import jakarta.persistence.*;
@Entity(name = "Departament")
public class Departament {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    
    private String name;

    public Long getId() {
        return id;
    }

    public String getDepartament() {
        return name;
    }

}
