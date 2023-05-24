package service;

import model.WorkOn;
import model.WorkPercentage;
import repository.WorkOnRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class HomeService {
    private WorkOnRepository workOnRepository = new WorkOnRepository();
    private WorkPercentage wp = null;
    private ArrayList<WorkOn> wlist;
    private ArrayList<Integer> statusList;
    private ArrayList<Integer> perList;

    public void loadWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get All employee work status
        wlist = workOnRepository.getAllUserWork();
        wp = new WorkPercentage(wlist);
        statusList = wp.getNumList();
        perList = wp.getPerList();

        req.setAttribute("statusList",statusList);
        req.setAttribute("perList",perList);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
