package model;

/**
 * Created by Idorf on 06-05-2016.
 */
public class Event {

    Integer userID;
    String title;
    String description;
    Integer date;
    String location;
    Integer catagoryID;
    Integer picturePath;

    public Event(Integer userID, String title, String description, Integer date, String location, Integer catagoryID, Integer picturePath) {
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.catagoryID = catagoryID;
        this.picturePath = picturePath;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCatagoryID() {
        return catagoryID;
    }

    public void setCatagoryID(Integer catagoryID) {
        this.catagoryID = catagoryID;
    }

    public Integer getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(Integer picturePath) {
        this.picturePath = picturePath;
    }
}