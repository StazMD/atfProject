package api;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserData {
    private String _id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer __v;

    public UserData() {
    }

    public UserData(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

}




