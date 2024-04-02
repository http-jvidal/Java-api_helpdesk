package api.helpdesk.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="chamados")
public class Ticket {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "detalhes")
    private String detalhes;

    @Column(name = "contato")
    private String contato;
    
    @Column(name = "nome")
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departamento_id")
    private Departament departamento;

    public Ticket(){

    }


    public Ticket(String nome, String detalhes, String contato, Departament departamento){
        this.nome = nome;
        this.detalhes = detalhes;
        this.contato = contato;
        this.departamento = departamento;
    }

    public Long getId() {
        return id;
    }


    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }


    public Departament getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departament departamento) {
        this.departamento = departamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public void setId(Long id) {
        this.id = id;
    }


    public String getDetalhes() {
        return detalhes;
    }


    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }


}
