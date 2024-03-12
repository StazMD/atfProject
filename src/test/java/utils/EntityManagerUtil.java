package utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class EntityManagerUtil {
    private static final String PERSISTENCE_UNIT_NAME = "AtfPersistenceUnit";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            try {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            } catch (PersistenceException ex) {
                System.err.println("Failed to create EntityManagerFactory: " + ex.getMessage());
                throw ex;
            }
        }
        return factory;
    }

    public static void getEntityManager() {
        getEntityManagerFactory();
    }

    public static void shutdownJpa() {
        if (factory != null) {
            factory.close();
        }
    }
}



