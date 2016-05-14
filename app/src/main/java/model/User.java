package model;

/**
 * Created by Idorf on 06-05-2016.
 */
public class User {

    private  Integer UserID;
    private String userName;
    private String email;
    private Integer pictureID;
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getPictureID() {
        return pictureID;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserID() {
        return UserID;
    }

    public User(String userName, String email, Integer pictureID, String password) {
        this.userName = userName;
        this.email = email;
        this.pictureID = pictureID;
        this.password = password;

    }
}


