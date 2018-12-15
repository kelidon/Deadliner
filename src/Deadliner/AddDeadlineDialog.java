package Deadliner;

import Deadliner.Deadline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class AddDeadlineDialog extends JDialog {

    AddDeadlineDialog() {
        setTitle("Add a deadline");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setPreferredSize(new Dimension(250, 400));
        setLayout(new BorderLayout());

        var navPanel = new JPanel();
        navPanel.setPreferredSize(new Dimension(250,45));
        Main.backIcon = new ImageIcon(Main.backIcon.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH));
        var back = new JRadioButton(Main.backIcon);
        navPanel.add(back);
        add(navPanel, BorderLayout.NORTH);

        TextField
                day = new TextField(),
                month = new TextField(),
                year = new TextField();
        var info = new TextArea();
        var tempDimension = new Dimension(70, 20);
        month.setPreferredSize(tempDimension);
        year.setPreferredSize(tempDimension);
        day.setPreferredSize(tempDimension);
        info.setPreferredSize(new Dimension(219, 280));

        var centralPane = new JPanel();
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
    private JButton buttonOK;
}
