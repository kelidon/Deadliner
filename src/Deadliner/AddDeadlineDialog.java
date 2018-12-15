package Deadliner;

import Deadliner.Deadline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class AddDeadlineDialog extends JDialog {
    private JPanel contentPane, centralPane = new JPanel();
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton back;

    AddDeadlineDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setPreferredSize(new Dimension(350, 600));
        setLayout(new BorderLayout());

        var navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1,5));
        navPanel.setPreferredSize(new Dimension(300,35));
        Main.backIcon = new ImageIcon(Main.backIcon.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH));
        back = new JRadioButton(Main.backIcon);
        navPanel.add(back);
        add(navPanel, BorderLayout.NORTH);

        TextField day = new TextField("");
        var month = new TextField("");
        var year = new TextField("");

        var info = new TextArea("");
        info.setPreferredSize(new Dimension(300, 350));

        centralPane.add(day);
        centralPane.add(month);
        centralPane.add(year);
        centralPane.add(info);
        add(centralPane, BorderLayout.CENTER);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Main.deadlines.add(new Deadline(info.getText(),
                            new Date(Integer.parseInt(year.getText()),
                                    Integer.parseInt(month.getText()),
                                    Integer.parseInt(day.getText()))));
                }catch(IllegalArgumentException exc){}
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
