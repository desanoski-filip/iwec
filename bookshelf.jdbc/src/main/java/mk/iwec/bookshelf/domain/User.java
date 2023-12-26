package mk.iwec.bookshelf.domain;

import java.util.Objects;

public class User {

    private String username;
    private String password; // You should hash and salt passwords in a real application.
    private UserType userType; // Enum representing user type (REGULAR or EDITOR)
    private String contactInfo;


    public User(String username, String password, UserType userType, String contactInfo) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.contactInfo = contactInfo;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User otherUser = (User) obj;
        return Objects.equals(username, otherUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "User{" +
                " Username='" + username + '\'' +
                ", User Type=" + userType +
                ", Contact Info='" + contactInfo + '\'' +
                '}';
    }
}


