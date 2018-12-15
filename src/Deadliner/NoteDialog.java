package Deadliner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static Deadliner.Main.ICON_SIZE;
import static Deadliner.TimetablePanel.week;

public class NoteDialog extends JDialog {

    public NoteDialog(int subjectIndex) {
        setContentPane(contentPane);
        setModal(true);
        setPreferredSize(NOTE_DIALOG_DIM);
        setLayout(new BorderLayout());

        var navPanel = new JPanel();
        navPanel.setPreferredSize(NAV_PANEL_DIM);
        Main.backIcon = new ImageIcon(Main.backIcon.getImage().getScaledInstance(ICON_SIZE,ICON_SIZE, Image.SCALE_SMOOTH));
        var back = new JRadioButton(Main.backIcon);
        navPanel.add(back);
        add(navPanel, BorderLayout.SOUTH);

        var note = new TextArea();
        note.setPreferredSize(NOTE_AREA_DIM);
        note.setText(week.classes[TimetablePanel.viewedDayNumber][subjectIndex].getNote());

        Class clicked = week.classes[TimetablePanel.viewedDayNumber][subjectIndex];

        var notePanel = new JPanel();
        var tempSubject = new TextField(clicked.getSubject());
        var tempRoom = new TextField(clicked.getRoom());
        var tempTime = new TextField(clicked.getTime());
        notePanel.add(tempRoom);
        notePanel.add(tempSubject);
        notePanel.add(tempTime);
        tempSubject.setEditable(false);
        tempRoom.setEditable(false);
        tempTime.setEditable(false);

        notePanel.add(note);
        add(notePanel,BorderLayout.CENTER);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked.setNote(note.getText());
                dispose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private JPanel contentPane;
    private final Dimension
            NOTE_DIALOG_DIM = new Dimension(250,350),
            NAV_PANEL_DIM = new Dimension(250,50),
            NOTE_AREA_DIM = new Dimension(211, 230);
}
