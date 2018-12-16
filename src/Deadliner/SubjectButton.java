package Deadliner;

import javax.swing.*;
import java.awt.*;

public class SubjectButton extends JButton {
    private String time, name, room;
    private int index;
    private final String space = "         ";

    public SubjectButton(Class lesson, int index, int viewedDay) {
        this.time = lesson.getTime();
        this.name = lesson.getSubject();
        this.room = lesson.getRoom();
        this.index = index;

        setLayout(new GridLayout(1, 3));
        add(new JLabel("<html>" + time + "</html>"));
        add(new JLabel(space + name));
        add(new JLabel(space + room));

        switch (lesson.getClassType()) {
            case "lecture": {
                setBackground(Color.yellow);
                break;
            }
            case "litklub": {
                setBackground(Color.red);
                break;
            }
            case "str": {
                setBackground(Color.cyan);
                break;
            }

        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
