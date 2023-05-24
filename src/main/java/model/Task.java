package model;

public class Task {
    String ID;
    String name;

    public Task(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    public Task(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
