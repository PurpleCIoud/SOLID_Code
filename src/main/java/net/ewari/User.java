package net.ewari;

public class User {
    private String username;
    private String password;

    // Constructor
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Getter methods
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    //Setter methods
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
