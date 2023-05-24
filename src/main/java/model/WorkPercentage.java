package model;

import java.util.ArrayList;

public class WorkPercentage {
    private ArrayList<Integer> numList;
    private ArrayList<WorkOn> list;
    public WorkPercentage(ArrayList<WorkOn> list) {
        this.list = list;
        this.numList = null;
    }

    public ArrayList<Integer> getPerList() {
        return numList=countWorkPercentage();
    }

    public ArrayList<Integer> getNumList(){
        return numList = countNumberOfWork();
    }
    public void setNumList(ArrayList<Integer> numList) {
        this.numList = numList;
    }

    public ArrayList<Integer> countWorkPercentage(){
        if(list==null) return null;

        numList = new ArrayList<>();
        int notDone = 0;
        int isDoing = 0;
        int done = 0;
        int total = 0;

        for(WorkOn w : list){
            if(w.getStatusID().equals("Chưa Thực Hiện") || w.getStatusID().equals("1")){
                notDone++;
            }else if(w.getStatusID().equals("Đang thực hiện") || w.getStatusID().equals("2")){
                isDoing++;
            }else{
                done++;
            }
        }

        total = notDone + isDoing + done;
        notDone = (int)((notDone*100)/total);
        isDoing = (int)((isDoing*100)/total);
        done = (int)((done*100)/total);

        numList.add(notDone);
        numList.add(isDoing);
        numList.add(done);

        return numList;
    }

    public ArrayList<Integer> countNumberOfWork(){
        if(list==null) return null;

        numList = new ArrayList<>();

        int notDone = 0;
        int isDoing = 0;
        int done = 0;
        int total = 0;

        for(WorkOn w : list){
            if(w.getStatusID().equals("Chưa Thực Hiện")){
                notDone++;
            }else if(w.getStatusID().equals("Đang thực hiện")){
                isDoing++;
            }else{
                done++;
            }
        }

        numList.add(notDone);
        numList.add(isDoing);
        numList.add(done);

        return numList;
    }
}
