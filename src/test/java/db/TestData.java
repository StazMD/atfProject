package db;

import context.ScenarioContext;
import entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ExceptionUtils;
import utils.JPAUtil;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestData {

    private static final Logger log = LogManager.getLogger(TestData.class);
    private static EntityManager em;
    private static final ScenarioContext scenarioContext = ScenarioContext.INSTANCE;

    public TestData() {
        em = JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    private UserEntity extractUserData() {
        try {
            return (UserEntity) scenarioContext.getContext("user");
        } catch (RuntimeException ex) {
            throw new ExceptionUtils("User context failed to extract");
        }
    }

    public void valuesAddedToTheDb() {
        UserEntity userEntity = extractUserData();
        userEntity.setCreatedAt(LocalDateTime.now());

        em.getTransaction().begin();
        try {
            em.persist(userEntity);
            em.getTransaction().commit();
            log.info("Entity inserted");
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            log.error("Reverted transaction due to persistence error", ex);
            throw ex;
        }
    }

    public void getUserEntityFromDatabase() {
        UserEntity userEntity = extractUserData();
        em.getTransaction().begin();
        UserEntity retrievedEntity = em.find(UserEntity.class, userEntity.getId());
        if (retrievedEntity == null) {
            throw new EntityNotFoundException("User Entity with ID " + userEntity.getId() + " not found");
        }
        assertEquals(userEntity.getFirstName(), retrievedEntity.getFirstName());
        assertEquals(userEntity.getLastName(), retrievedEntity.getLastName());
        assertEquals(userEntity.getEmail(), retrievedEntity.getEmail());
        log.info("Entity verified");
    }

    public void assertThatUserWasNotCreated(String fieldName) {
        UserEntity userEntity = extractUserData();
        em.getTransaction().begin();
        String queryStr = String.format("SELECT COUNT(u) FROM UserEntity u WHERE u.%s = :%s", fieldName, fieldName);
        Query query = em.createQuery(queryStr);
        switch (fieldName) {
            case "firstName" -> query.setParameter("firstName", userEntity.getFirstName());
            case "lastName" -> query.setParameter("lastName", userEntity.getLastName());
            case "email" -> query.setParameter("email", userEntity.getEmail());
            case "password" -> query.setParameter("password", userEntity.getPassword());
        }
        Long count = (Long) query.getSingleResult();
        em.getTransaction().commit();
        if (count == 0L) {
            log.info("Entity is not there");
        } else {
            throw new PersistenceException("Entity unexpectedly found in the database");
        }
    }

    public void queryUpdateDatabase(String query) {
        em.getTransaction().begin();
        try {
            em.createQuery(query).executeUpdate();
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            log.error("Failed to execute update query", ex);
            throw ex;
        }
    }
}