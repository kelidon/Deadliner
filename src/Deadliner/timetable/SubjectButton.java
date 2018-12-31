package deadliner.timetable;

import javax.swing.*;
import java.awt.*;

public class SubjectButton extends JButton {

    public SubjectButton(Class lesson, int index) {
        super();
        this.time = lesson.getTime();
        this.name = lesson.getSubject();
        this.room = lesson.getRoom();
        this.index = index;

        setLayout(new GridBagLayout());

        var constraints = new GridBagConstraints();
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.NONE;

        constraints.anchor = GridBagConstraints.WEST;
        add(new JLabel("<html>" + time + "</html>"), constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        add(new JLabel(name), constraints);
        constraints.anchor = GridBagConstraints.EAST;
        add(new JLabel(room), constraints);

        switch (lesson.getClassType()) {
            case "lecture": {
                setBorder(new RoundedBorder(BORDER_RADIUS, BORDER_WIDTH, new Color(245,244,117)));
                break;
            }
            case "litklub": {
                setBorder(new RoundedBorder(BORDER_RADIUS, BORDER_WIDTH, new Color(242,139,130)));
                break;
            }
            case "str": {
                setBorder(new RoundedBorder(BORDER_RADIUS, BORDER_WIDTH, new Color(167,255, 235)));
                break;
            }
            case "regular":{
                setBorder(new RoundedBorder(BORDER_RADIUS, BORDER_WIDTH, Color.white));
                break;
            }
            default:{
                if(index<3) {
                    setBorder(new RoundedBorder(BORDER_RADIUS, BORDER_WIDTH, Color.white));
                }
                else
                    setBorder(new RoundedBorder(0, 0, Color.white, Color.white));
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

    private String
            time,
            name,
            room;
    private int index;
    private final int
            BORDER_RADIUS = 25,
            BORDER_WIDTH = 2;

}
