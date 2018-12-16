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
        this.time       = "";
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

    public static Class fromString(String classInfo){
        Scanner scanner = new Scanner(classInfo);
        Class aClass = new Class();
        aClass.subject = scanner.next();
        aClass.room = scanner.next();
        if(aClass.room.equals("0"))
            aClass.room = "";
        aClass.classType = scanner.next();

        return aClass;
    }

    private String subject;
    private String room;
    private String classType;
    private String note;
    private String time;
}