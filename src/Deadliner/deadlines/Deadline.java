package deadliner.deadlines;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline {

    // this are subject to change. Also we may replace getDeadline with getDate, getTime etc...
    private static final String INPUT_FORMAT = "d[d]-M[M]-y[y] HH-mm";
    private static final String PRINT_FORMAT_NO_TIME = "dd/MM/yyyy";
    private static final String PRINT_FORMAT_WITH_TIME = "dd/MM/yy    HH-mm";

    // I think we can remove this one.
    public Deadline() {
        this.informativeTitle = "NOT SET";
        this.deadline = null;
    }

    public Deadline(String informativeTitle, String dateString, String timeString) {
        this.informativeTitle = informativeTitle;
        this.isDueTime = true;
        this.deadline = LocalDateTime.parse(
                dateString + " " + timeString,
                DateTimeFormatter.ofPattern(INPUT_FORMAT)
        );
    }

    public Deadline(String informativeTitle, String ddMMyy) {
        this.informativeTitle = informativeTitle;
        this.isDueTime = false;
        this.deadline = LocalDateTime.parse(
                ddMMyy + " 23-59",
                DateTimeFormatter.ofPattern(INPUT_FORMAT)
        );
    }

    public String getTitle() {
        return informativeTitle;
    }

    public void setTitle(String informativeTitle) {
        this.informativeTitle = informativeTitle;
    }

    public String getDeadline() {
        return isDueTime ? deadline.format(DateTimeFormatter.ofPattern(PRINT_FORMAT_WITH_TIME)) : deadline.format(DateTimeFormatter.ofPattern(PRINT_FORMAT_NO_TIME));
    }

//    TODO: add setDeadline to configure deadline after creation
//    public void setDeadline( ... ){}

    private String informativeTitle;
    private LocalDateTime deadline;
    private boolean isDueTime;
//    TODO : add field for extra details (e.g. note itslef / path to file / etc...)
}
