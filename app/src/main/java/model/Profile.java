package model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Profile {

    public String firstName;
    public String middleName;
    public String lastName;
    public String dateOfBirth;

    public Profile() {
    }

    public Profile(String firstName, String middleName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}
