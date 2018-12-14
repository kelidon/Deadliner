package Deadliner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NoteDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton back;

    public NoteDialog(int subjectIndex) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setPreferredSize(new Dimension(400, 600));
        setLayout(new BorderLayout());

        var navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1,5));
        navPanel.setPreferredSize(new Dimension(300,35));
        Main.backIcon = new ImageIcon(Main.backIcon.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH));
        back = new JRadioButton(Main.backIcon);
        navPanel.add(back);
        add(navPanel, BorderLayout.NORTH);

        var note = new TextArea();
        note.setPreferredSize(new Dimension(300, 350));
        note.setText(Main.week.classes[Main.viewedDayNumber][subjectIndex].getNote());

        Class clicked = Main.week.classes[Main.viewedDayNumber][subjectIndex];
        var notePanel = new JPanel();
        var tempSubject = new TextField(clicked.getSubject());
        notePanel.add(tempSubject);
        var tempRoom = new TextField(clicked.getRoom());
        notePanel.add(tempRoom);
        var tempTime = new TextField(clicked.getTime());
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
//                clicked.setSubject(tempSubject.getText());
//                clicked.setRoom(tempRoom.getText());
//                clicked.setTime(tempTime.getText());
                dispose();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
