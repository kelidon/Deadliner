package Deadliner;

import javax.swing.*;
import java.awt.*;

public class SubjectButton extends JButton {

    private JLabel time;
    private JLabel subject;
    private JLabel room;
    public SubjectButton(Class lesson, int index, int viewedDay) {
        super();
        task = lesson;
        this.index = index;

        setLayout(new GridBagLayout());

        var constraints = new GridBagConstraints();
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;

        time = new JLabel("<html>" + task.getTime() + "</html>");
        subject = new JLabel(task.getSubject());
        room = new JLabel(task.getRoom());
        add(time, constraints);
        add(subject, constraints);
        add(room, constraints);

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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        time.setText("<html>" + task.getTime() + "</html>");
        subject.setText(task.getSubject());
        room.setText(task.getRoom());
    }

    @Override
    public String getName() {
        return task.getSubject();
    }

    public String getTime() {
        return task.getTime();
    }

    public String getRoom() {
        return task.getRoom();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void setName(String name) {
        task.setSubject(name);
    }

    public void setTime(String time) {
        task.setTime(time);
    }

    public void setRoom(String room) {
        task.setRoom(room);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private Class task;
    private int index;
}
