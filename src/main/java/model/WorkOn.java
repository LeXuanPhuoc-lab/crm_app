package model;

public class WorkOn {
    String workOnID;
    String projectID;
    String taskID;
    String employeeID;
    String createDate;
    String endDate;
    String statusID;

    public WorkOn(String workOnID, String taskID, String projectID, String employeeID, String createDate, String endDate, String statusID) {
        this.workOnID = workOnID;
        this.projectID = projectID;
        this.taskID = taskID;
        this.employeeID = employeeID;
        this.createDate = createDate;
        this.endDate = endDate;
        this.statusID = statusID;
    }

    public WorkOn(String taskID,String projectID, String employeeID, String createDate, String endDate, String statusID) {
        this.projectID = projectID;
        this.taskID = taskID;
        this.employeeID = employeeID;
        this.createDate = createDate;
        this.endDate = endDate;
        this.statusID = statusID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
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

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getWorkOnID() {
        return workOnID;
    }

    public void setWorkOnID(String workOnID) {
        this.workOnID = workOnID;
    }
}
