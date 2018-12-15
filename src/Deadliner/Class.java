package Deadliner;

import java.util.Scanner;

/**
 * Created by Kelidon on 14.11.2018.
 */

public class Class {

    Class(){
        this("", "", "regular");
    }

    public Class(String subject, String room, String classType) {
        this.subject    = subject;
        this.room       = room;
        this.classType  = classType;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }

    public String getRoom() {
        return room;
    }

    public String getClassType() {
        return classType;
    }

    public String getNote() {
        return note;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    void setFromString(String classInfo){
        Scanner scanner = new Scanner(classInfo);
        this.subject = scanner.next();
        this.room = scanner.next();
        if(room.equals("0"))
            room = "";
        this.classType = scanner.next();
        this.note = "";
    }

    private String subject;
    private String room;
    private String classType;
    private String note;
    private String time;
}