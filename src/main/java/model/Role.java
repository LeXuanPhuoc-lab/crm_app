package model;

public class Role {
    String ID;
    String name;
    String createDate;
    String describe;

    public Role(){}
    public Role(String ID, String name, String createDate, String describe) {
        this.ID = ID;
        this.name = name;
        this.createDate = createDate;
        this.describe = describe;
    }


    public Role(String name, String createDate, String describe) {
        this.name = name;
        this.createDate = createDate;
        this.describe = describe;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
