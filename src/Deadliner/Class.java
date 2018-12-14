package Deadliner;

import java.util.Scanner;

/**
 * Created by Kelidon on 14.11.2018.
 */

public class Class {
    private String subject, room, classType, note, time;
    Class(){
        this.subject = "";
        this.room = "";
        this.classType = "regular";
    }

    public Class(String subject, String room, String classType) {
        this.subject = subject;
        this.room = room;
        this.classType = classType;
    }

    String getTime() {
        return time;
    }

    void setTime(String time) {
        this.time = time;
    }

    String getSubject() {
        return subject;
    }

    void setSubject(String subject) {
        this.subject = subject;
    }

    String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    String getClassType() {
        return classType;
    }

    public String getNote() {
        return note;
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
}