package api.helpdesk.domain.models;

import jakarta.persistence.*;


@Entity(name="chamados")
public class Chamados {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "detalhes")
    String detalhes;

    @Column(name = "imagem")
    String imagem;
    

    @Column(name = "estado")
    Boolean estado;


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


    public Boolean getEstado() {
        return estado;
    }


    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
}
