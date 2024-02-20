package api.helpdesk.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity(name="chamados")
public class Ticket {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "detalhes")
    private String detalhes;

    @Column(name = "imagem")
    private String imagem;
    
    @Column(name = "nome")
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departamento_id")
    private Departament departamento;


    @Column(name = "estado")
    private String estado;


    public Ticket(){

    }

    public Ticket(String nome, String detalhes, String imagem, Departament departamento){
        this.nome = nome;
        this.detalhes = detalhes;
        this.imagem = imagem;
        this.departamento = departamento;
    }

    public Long getId() {
        return id;
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


    public String getImagem() {
        return imagem;
    }


    public void setImagem(String imagem) {
        this.imagem = imagem;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    
}
