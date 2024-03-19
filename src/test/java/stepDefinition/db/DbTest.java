package stepDefinition.db;

import context.ScenarioContext;
import entity.UserEntity;
import enums.Credentials;
import enums.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CustomException;
import utils.EntityManagerUtil;

import java.time.LocalDateTime;

import static enums.Credentials.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DbTest {

    private final Logger log = LogManager.getLogger(DbTest.class);
    private final EntityManager em = EntityManagerUtil.getEntityManager();
    private final ScenarioContext scenarioContext = ScenarioContext.INSTANCE;

    private UserEntity extractUserData() {
        try {
            return (UserEntity) scenarioContext.getContext(Entity.USER);
        } catch (RuntimeException ex) {
            throw new CustomException("User context failed to extract");
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
            throw new CustomException(ex.getMessage(), ex);
        }
    }

    public void getUserEntityFromDatabase() {
        UserEntity userEntity = extractUserData();
        em.getTransaction().begin();
        UserEntity retrievedEntity = em.find(UserEntity.class, userEntity.getId());
        if (retrievedEntity == null) {
            throw new EntityNotFoundException("User Entity with ID " + userEntity.getId() + " not found");
        }
        assertThat(userEntity.getFirstName()).isEqualTo(retrievedEntity.getFirstName());
        assertThat(userEntity.getLastName()).isEqualTo(retrievedEntity.getLastName());
        assertThat(userEntity.getEmail()).isEqualTo(retrievedEntity.getEmail());
        log.info("Entity verified");
    }

    public void assertThatUserWasNotCreated(String fieldName) {
        UserEntity userEntity = extractUserData();
        em.getTransaction().begin();
        String queryStr = String.format("SELECT COUNT(u) FROM UserEntity u WHERE u.%s = :%s", fieldName, fieldName);
        Query query = em.createQuery(queryStr);
        Credentials credentials;
        try {
            credentials = Credentials.valueOf(fieldName.toUpperCase());
        } catch (IllegalArgumentException ex) {
            log.error("Provided unknown field: {}", fieldName);
            throw new CustomException(ex.getMessage());
        }
        switch (credentials) {
            case FIRSTNAME -> query.setParameter(FIRSTNAME.getValue(), userEntity.getFirstName());
            case LASTNAME -> query.setParameter(LASTNAME.getValue(), userEntity.getLastName());
            case EMAIL -> query.setParameter(EMAIL.getValue(), userEntity.getEmail());
            case PASSWORD -> query.setParameter(PASSWORD.getValue(), userEntity.getPassword());
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
            throw new CustomException(ex.getMessage(), ex);
        }
    }
}
