package deadliner.deadlines;

import java.util.Date;

public class Deadline {
    private String info;
    private Date deadlineDate;
    Deadline(){
        this.info = "";
        this.deadlineDate = null;
    }

    Deadline(String info, Date deadlineDate) {
        this.info = info;
        this.deadlineDate = deadlineDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
}
