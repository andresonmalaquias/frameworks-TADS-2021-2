package ifam.edu.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true, nullable = false)
    private String documento;
    private String nome;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @ManyToMany(mappedBy = "pessoas", fetch = FetchType.EAGER)
    private List<Interesse> interesses = new ArrayList();

    public List<Interesse> getInteresses() {
        return interesses;
    }

    public void addInteresse(Interesse interesse){
        if(!this.interesses.contains(interesse)){
            this.interesses.add(interesse);
            interesse.addPessoa(this);
        }
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cidade cidade;

    public String getDocumento() {
        return documento;
    }

    public Integer getId() { return id; }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cidade=" + cidade +
                '}';
    }
}
