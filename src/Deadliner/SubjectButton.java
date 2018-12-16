package Deadliner;

import javax.swing.*;
import java.awt.*;

public class SubjectButton extends JButton {

    public SubjectButton(Class lesson, int index, int viewedDay) {
        super();
        this.time = lesson.getTime();
        this.name = lesson.getSubject();
        this.room = lesson.getRoom();
        this.index = index;

        setBorder(new RoundedBorder(BORDER_RADIUS));
        setLayout(new GridBagLayout());

        var constraints = new GridBagConstraints();
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;

        add(new JLabel("<html>" + time + "</html>"), constraints);
        add(new JLabel(name), constraints);
        add(new JLabel(room), constraints);

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

    @Override
    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getRoom() {
        return room;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private String time, name, room;
    private int index;
    private final int BORDER_RADIUS = 25;

}
