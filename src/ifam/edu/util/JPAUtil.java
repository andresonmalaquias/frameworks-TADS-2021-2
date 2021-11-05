package ifam.edu.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAAula");

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
