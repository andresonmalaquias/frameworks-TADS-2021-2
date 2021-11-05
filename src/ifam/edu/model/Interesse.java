package ifam.edu.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Interesse {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String descricao;

    @ManyToMany
    private List<Pessoa> pessoas = new ArrayList();

    public Interesse(String descricao){
        this.descricao = descricao;
    }

    public Interesse(){}

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void addPessoa(Pessoa pessoa) {
        if(!this.pessoas.contains(pessoa)){
            this.pessoas.add(pessoa);
            pessoa.addInteresse(this);
        }

    }

    @Override
    public String toString() {
        return "Interesse{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", pessoas=" + pessoas +
                '}';
    }
}
