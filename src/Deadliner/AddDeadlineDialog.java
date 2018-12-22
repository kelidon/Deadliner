package Deadliner;

import Deadliner.Deadline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import static Deadliner.Main.ICON_SIZE;

public class AddDeadlineDialog extends JDialog {

    AddDeadlineDialog(ArrayList<Deadline> deadlines) {
        setTitle(ADD_DIALOG_TITLE);
        setContentPane(contentPane);
        setModal(true);
        setPreferredSize(ADD_DIALOG_DIM);
        setLayout(new BorderLayout());

        var navPanel = new JPanel();
        navPanel.setPreferredSize(NAV_PANEL_DIM);
        var back = new JRadioButton(Main.backIcon);
        navPanel.add(back);
        add(navPanel, BorderLayout.SOUTH);

//        TextField
//                day = new TextField(),
//                month = new TextField(),
//                year = new TextField();
//        var tempDimension = new Dimension(DATE_FIELDS_DIM);
//        month.setPreferredSize(tempDimension);
//        year.setPreferredSize(tempDimension);
//        day.setPreferredSize(tempDimension);
        var info = new TextField();
        info.setPreferredSize(INFO_DIM);

        var centralPane = new JPanel();
//        centralPane.add(day);
//        centralPane.add(month);
//        centralPane.add(year);
        centralPane.add(info);
        add(centralPane, BorderLayout.CENTER);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    deadlines.add(new Deadline(info.getText()
//                            ,
//                            new Date(Integer.parseInt(year.getText()) - YEAR_SHIFT,
//                                    Integer.parseInt(month.getText()),
//                                    Integer.parseInt(day.getText()))
                    ));
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
    private final Dimension
            ADD_DIALOG_DIM = new Dimension(150,115),
            NAV_PANEL_DIM = new Dimension(250,50),
            DATE_FIELDS_DIM = new Dimension(70, 20),
            INFO_DIM = new Dimension(130, 20);
    private final String ADD_DIALOG_TITLE = "Add a deadline";
    private final int YEAR_SHIFT = 1900;
}
