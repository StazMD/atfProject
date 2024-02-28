//package db;
//
//import entity.User;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//public class Dao {
//
//    private static final Logger log = LogManager.getLogger(Dao.class);
//    private static EntityManagerFactory emf;
//    private static EntityManager em;
//
//    @BeforeAll
//    public static void setUp() {
//        try {
//            emf = Persistence.createEntityManagerFactory("testPU");
//            log.info("EntityManager was successfully initialized.");
//            em = emf.createEntityManager();
//        } catch (Exception e) {
//            log.error("Error initializing EntityManager: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    @Test
//    public void addEntity() {
////        EntityClass entity = new EntityClass("Test Name", 25, "test@example.com", LocalDateTime.now());
//        User entity = new User();
//
//        entity.setFirstName("afaa");
//        entity.setLastName("sdsa");
//        entity.setEmail("avvddsd@mail.md");
//        entity.setCreatedAt(LocalDateTime.now());
//
//        em.getTransaction().begin();
//        em.persist(entity);
//        em.getTransaction().commit();
//
//        User retrievedEntity = em.find(User.class, entity.getId());
//        assert retrievedEntity != null;
////        assert "Test Name".equals(retrievedEntity.getName());
//        log.info("Entity inserted and verified");
//    }
//
//    @AfterAll
//    public static void afterAll() {
//        if (em != null) {
//            em.close();
//            log.info("EntityManager was successfully closed.");
//        }
//        if (emf != null) {
//            emf.close();
//            log.info("EntityManagerFactory was successfully closed.");
//        }
//    }
//
//}
