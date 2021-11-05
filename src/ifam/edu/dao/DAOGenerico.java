package ifam.edu.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DAOGenerico<Tipo> {

    private EntityManager entityManager;
    private Class<Tipo> classe;

    public DAOGenerico(EntityManager entityManager, Class classe){
        this.entityManager = entityManager;
        this.classe = classe;
    }

    public void salvar(Tipo object){

        entityManager.persist(object);

    }

    public Tipo consultar(Integer id){

        return entityManager.find(classe, id);
    }

    public void remover(Integer id){

        Tipo object = entityManager.find(classe, id);

        entityManager.remove(object);

    }

    public List<Tipo> listar(){

        Query query = entityManager.createQuery("select o from "+ classe.getName() +" o");

        return query.getResultList();
    }
}
