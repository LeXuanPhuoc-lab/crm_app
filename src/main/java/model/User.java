package model;

import java.util.ArrayList;

public class User {
    String ID;
    String firstname;
    String lastname;
    String username;
    String password;
    String phone;
    String avartar;
    String roleID;

    String countryID;

    ArrayList<WorkOn> notDoneList;
    ArrayList<WorkOn> isDoingList;
    ArrayList<WorkOn> doneList;

    public User(){
    }
    public User(String ID, String firstname, String lastname
            , String username, String password
            , String phone, String roleID
            , String countryID) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.roleID = roleID;
        this.countryID = countryID;
    }

    public User(String firstname, String lastname
            , String username, String password
            , String phone, String roleID
            , String countryID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.roleID = roleID;
        this.countryID = countryID;
    }

    public User(String ID, String firstname, String lastname, String username, String avartar, String phone) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.phone = phone;
        this.avartar = avartar;
        this.notDoneList = new ArrayList<>();
        this.isDoingList = new ArrayList<>();
        this.doneList = new ArrayList<>();
    }

    public User(String ID, String firstname, String lastname, String username, String roleID) {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.roleID = roleID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public ArrayList<WorkOn> getNotDoneList() {
        return notDoneList;
    }

    public void setNotDoneList(ArrayList<WorkOn> notDoneList) {
        this.notDoneList = notDoneList;
    }

    public ArrayList<WorkOn> getIsDoingList() {
        return isDoingList;
    }

    public void setIsDoingList(ArrayList<WorkOn> isDoingList) {
        this.isDoingList = isDoingList;
    }

    public ArrayList<WorkOn> getDoneList() {
        return doneList;
    }

    public void setDoneList(ArrayList<WorkOn> doneList) {
        this.doneList = doneList;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }
}
