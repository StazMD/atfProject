package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.TestDataGeneratorUtils;

import java.time.LocalDateTime;

public class Dao {

    private static final Logger log = LogManager.getLogger(TestDataGeneratorUtils.class);
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    public static void setUp() {
        try {
            emf = Persistence.createEntityManagerFactory("testPU");
            log.info("EntityManager was successfully initialized.");
            em = emf.createEntityManager();
        } catch (Exception e) {
            log.error("Error initializing EntityManager: " + e.getMessage());
            throw e;
        }
    }

//    @Test
//    public void testConnection() {
//        em.getTransaction().begin();
//        em.createNativeQuery("SELECT 1").getResultList();
//        em.getTransaction().commit();
//        assertNotNull(em);
//    }

    @Test
    public void addEntity() {
        EntityClass entity = new EntityClass("Test Name", 25, "test@example.com", LocalDateTime.now());

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();

        // Проверки после вставки, например, можно получить запись из базы и сравнить
        EntityClass retrievedEntity = em.find(EntityClass.class, entity.getId());
        assert retrievedEntity != null;
        assert "Test Name".equals(retrievedEntity.getName());
        log.info("Entity inserted and verified");
    }

    @AfterAll
    public static void afterAll() {
        if (em != null) {
            em.close();
            log.info("EntityManager was successfully closed.");
        }
        if (emf != null) {
            emf.close();
            log.info("EntityManagerFactory was successfully closed.");
        }
    }

}


