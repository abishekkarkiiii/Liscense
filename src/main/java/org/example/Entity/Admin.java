package org.example.Entity;

public class Admin {
    private String userName;
    private String password;

    public Admin(String username,String password) {
        this.userName=userName;
        this.password=password;

    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    //Yesari ni garna chai milxa but i prefer lombok
}
