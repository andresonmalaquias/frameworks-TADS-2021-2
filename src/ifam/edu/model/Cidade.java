package ifam.edu.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cidade {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String nome;

    @ManyToOne
    private  Estado estado;

    @OneToMany(mappedBy = "cidade")
    private List<Pessoa> pessoas;

    public Cidade() {}

    public Integer getId() { return id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estado=" + estado +
                '}';
    }
}
