package service;

import model.Task;
import repository.TaskRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskService {
    private TaskRepository taskRepository = new TaskRepository();

    public ArrayList<Task> getAllTask(){
        return taskRepository.getAllTask();
    }

    public void addTask(String name) {
        taskRepository.addTask(new Task(name));
    }

    public void removeTask(int ID) {
        taskRepository.removeTask(ID);
    }

    public void updateTask(int ID, String name) {
        taskRepository.updateTask(new Task(String.valueOf(ID), name));
    }

    public Task getTaskByID(int ID) {
        return taskRepository.getTaskByID(ID);
    }

    public void searchTask(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String txtSearch = (String)req.getParameter("txt");
        ArrayList<Task> list = taskRepository.searchTaskByName(txtSearch);
        PrintWriter pw = resp.getWriter();
        for(Task t : list){
            pw.println(" <tr>\n" +
                    "                                                    <td>" + t.getID() + "</td>\n" +
                    "                                                    <td>" + t.getName() + "</td>\n" +
                    "                                                    <td style=\"display: flex;\">\n" +
                    "                                                        <form action=\"task\" method=\"GET\">\n" +
                    "                                                            <input type=\"hidden\" value=\"${t.ID}\" name=\"updateTaskID\">\n" +
                    "                                                            <button type=\"submit\" class=\"btn btn-sm btn-primary\">Sửa</button>\n" +
                    "                                                        </form>\n" +
                    "                                                        <form action=\"task\" method=\"GET\">\n" +
                    "                                                            <input type=\"hidden\" name=\"removeTaskID\" value=\"${t.ID}\">\n" +
                    "                                                            <button type=\"submit\" class=\"btn btn-sm btn-danger\" style=\"margin-left:5px;\">Xóa</button>\n" +
                    "                                                        </form>\n" +
                    "                                                    </td>\n" +
                    "                                                </tr>");
        }
        pw.close();
    }
}
