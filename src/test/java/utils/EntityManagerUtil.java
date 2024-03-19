//package utils;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.PersistenceException;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class EntityManagerUtil {
//    private static final String PERSISTENCE_UNIT_NAME = "AtfPersistenceUnit";
//    private static EntityManagerFactory factory;
//    private static final Logger log = LogManager.getLogger(EntityManagerUtil.class);
//
//    private static EntityManagerFactory getEntityManagerFactory() {
//        if (factory == null) {
//            try {
//                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//            } catch (PersistenceException ex) {
//                log.error("Failed to create EntityManagerFactory: {}", ex.getMessage());
//                throw new CustomException(ex.getMessage(), ex);
//            }
//        }
//        return factory;
//    }
//
//    public static EntityManager getEntityManager() {
//        return getEntityManagerFactory().createEntityManager();
//    }
//
//    public static void shutdownJpa() {
//        if (factory != null) {
//            factory.close();
//        }
//    }
//}
//
//
//
