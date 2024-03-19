package enums;

public enum Credentials {
    RANDOM("[random"),
    FIRSTNAME("firstName"),
    LASTNAME("lastName"),
    EMAIL("email"),
    PASSWORD("password"),
    TOKEN("token");

    private final String value;

    Credentials(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
