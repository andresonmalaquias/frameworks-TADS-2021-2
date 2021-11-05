package ifam.edu.dao;

import ifam.edu.model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PessoaDAO {
    private EntityManager entityManager;
    private DAOGenerico<Pessoa> dao;

    public PessoaDAO(EntityManager entityManager){
        this.entityManager = entityManager;
        dao = new DAOGenerico<>(entityManager, Pessoa.class);
    }

    public void salvar(Pessoa pessoa){
        dao.salvar(pessoa);
    }

    public void consultar(Integer id){
        dao.consultar(id);
    }

    public void remover(Integer id){
        dao.remover(id);
    }

    public List<Pessoa> listar(){
        return dao.listar();
    }

    public List<Pessoa> listarPorNome(String nome){
        Query query = entityManager.createQuery("select p from Pessoa p where p.nome = :parNome");

        query.setParameter("parNome", nome);

        return  query.getResultList();
    }

    public List<Pessoa> listarPorNomeParcial(String nome){
        Query query = entityManager.createQuery("select p from Pessoa p where p.nome like :parNome");

        query.setParameter("parNome", "%"+nome+"%");

        return  query.getResultList();
    }

}
