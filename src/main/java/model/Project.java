package model;

public class Project {
    String ID;
    String name;
    String createDate;
    String endDate;


    public Project(String ID, String name, String createDate, String endDate) {
        this.ID = ID;
        this.name = name;
        this.createDate = createDate;
        this.endDate = endDate;
    }

    public Project(String name, String createDate, String endDate) {
        this.name = name;
        this.createDate = createDate;
        this.endDate = endDate;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
