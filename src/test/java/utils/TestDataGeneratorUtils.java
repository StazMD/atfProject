package utils;

import com.github.javafaker.Faker;
import context.ScenarioContext;
import entity.ContactEntity;
import entity.UserEntity;
import enums.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;

public class TestDataGeneratorUtils {

    private final Logger log = LogManager.getLogger(TestDataGeneratorUtils.class);

    private static final Faker faker = new Faker();

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomPassword() {
        int minLength = 7;
        int maxLength = 10;
        return faker.internet().password(minLength, maxLength);
    }

    public static String getRandomEmail() {
        return faker.internet().safeEmailAddress();
    }

    public static String getNegativeRandomFirstName() {
        return faker.lorem().characters(21);
    }

    public static String getNegativeRandomLastName() {
        return faker.lorem().characters(21);
    }

    public static String getNegativeRandomEmail() {
        return faker.lorem().characters(5);
    }

    public static String getNegativeRandomPassword() {
        int minLength = 1;
        int maxLength = 6;
        return faker.internet().password(minLength, maxLength);
    }

    public static String getRandomBirthdate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(faker.date().birthday());
    }

    public static String getRandomPhoneNumber() {
        return faker.numerify("##########");
    }

    public static String getRandomStreetAddress() {
        return faker.address().streetAddress();
    }

    public static String getRandomCity() {
        return faker.address().cityName();
    }

    public static String getRandomState() {
        return faker.address().state();
    }

    public static String getRandomPostalCode() {
        return faker.address().zipCode();
    }

    public static String getRandomCountry() {
        return faker.address().country();
    }

    public static String getRandomCredentials(String field) {
        return switch (field) {
            case "firstName" -> getRandomFirstName();
            case "lastName" -> getRandomLastName();
            case "email" -> getRandomEmail();
            case "password" -> getRandomPassword();
            default -> throw new CustomException("Unexpected value: " + field);
        };
    }

    public void generateUserCredentials() {
        UserEntity userEntity = new UserEntity(
                getRandomFirstName(),
                getRandomLastName(),
                getRandomEmail(),
                getRandomPassword()
        );
        ScenarioContext.INSTANCE.setContext(Entity.USER, userEntity);
        log.info("User credentials generated successfully");
    }

    public void generateContactCredentials() {
        ContactEntity contactEntity = new ContactEntity(
                getRandomFirstName(),
                getRandomLastName(),
                getRandomBirthdate(),
                getRandomEmail(),
                getRandomPhoneNumber(),
                getRandomStreetAddress(),
                getRandomStreetAddress(),
                getRandomCity(),
                getRandomState(),
                getRandomPostalCode(),
                getRandomCountry()
        );
        ScenarioContext.INSTANCE.setContext(Entity.CONTACT, contactEntity);
        log.info("Contact credentials generated successfully");
    }
}
