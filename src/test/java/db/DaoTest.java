package db;

import db.entities.Members;
import db.util.JPAUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.persistence.EntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaoTest {

    private static final Logger log = LogManager.getLogger(DaoTest.class);
    private static EntityManager em;

    public DaoTest() {
        em = JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    @When("values {string}, {string}, {string}, {string} added to the database")
    public void valuesAddedToTheDb(String firstName, String lastName, String email, String password) {
        Members members = new Members();
        members.setFirstName(firstName);
        members.setLastName(lastName);
        members.setEmail(email);
        members.setPassword(password);
        members.setCreatedAt(LocalDateTime.now());

        em.getTransaction().begin();
        em.persist(members);
        em.getTransaction().commit();

        log.info("Entity inserted");

    }

    @Then("values {string}, {string}, {string}, {string} is in the database")
    public void valuesIsInTheDatabase(String firstName, String lastName, String email, String password) {
        Members members = new Members();
        Members retrievedEntity = em.find(Members.class, members.getId());
        assert retrievedEntity != null;
        assertEquals(firstName, retrievedEntity.getFirstName());
        assertEquals(lastName, retrievedEntity.getLastName());
        assertEquals(email, retrievedEntity.getEmail());
        assertEquals(password, retrievedEntity.getPassword());
        log.info("Entity verified");

    }
}
