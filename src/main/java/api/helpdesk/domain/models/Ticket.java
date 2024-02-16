package api.helpdesk.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="chamados")
public class Ticket {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private Long id;

    @Column(name = "detalhes")
    private String detalhes;

    @Column(name = "imagem")
    private String imagem;
    
    @Column(name = "nome")
    private String nome;

    @Column(name = "Contato")
    private String contato;

    @Column(name = "estado")
    private String estado;

    public Long getId() {
        return id;
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
