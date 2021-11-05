package ifam.edu.dao;

import ifam.edu.model.Cidade;
import ifam.edu.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CidadeDAO {
    public void salvar(Cidade cidade){
        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(cidade);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public Cidade consultar(Integer id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        return entityManager.find(Cidade.class, id);
    }

    public void remover(Integer id){
        EntityManager entityManager = JPAUtil.getEntityManager();

        entityManager.getTransaction().begin();

        Cidade cidade = entityManager.find(Cidade.class, id);

        entityManager.remove(cidade);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public List<Cidade> listar(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Query query = entityManager.createQuery("select c from Cidade c");

        return query.getResultList();
    }
}
