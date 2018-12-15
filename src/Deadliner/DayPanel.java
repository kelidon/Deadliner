package Deadliner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Deadliner.Main.NUMBER_OF_CLASSES;

class DayPanel extends JPanel implements MouseListener {
    private SubjectButton[] subjectButtons = new SubjectButton[6];
    private NoteDialog noteDialog;

    DayPanel(Week week, int day) {
        setLayout(new GridLayout(NUMBER_OF_CLASSES, 1));
        setPreferredSize(new Dimension(300, 500));
        for (int i = 0; i < NUMBER_OF_CLASSES; i++) {
            subjectButtons[i] = new SubjectButton(week.classes[day][i], i, day);
            add(subjectButtons[i]);
            subjectButtons[i].addMouseListener(this);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //if (SwingUtilities.isLeftMouseButton(e)) {
        SubjectButton clicked = ((SubjectButton) e.getSource());
        int subjectIndex = clicked.getIndex();
        noteDialog = new NoteDialog(subjectIndex);
        noteDialog.pack();
        noteDialog.setVisible(true);
        //} else if (SwingUtilities.isRightMouseButton(e)) {

        //}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}