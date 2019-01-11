package deadliner.deadlines;

import deadliner.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddDeadlineDialog extends JDialog {

    AddDeadlineDialog() {
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

        TextField
                day = new TextField(),
                month = new TextField(),
                year = new TextField();
        var info = new TextArea();
        var tempDimension = new Dimension(DATE_FIELDS_DIM);
        month.setPreferredSize(tempDimension);
        year.setPreferredSize(tempDimension);
        day.setPreferredSize(tempDimension);
        info.setPreferredSize(INFO_DIM);

        var centralPane = new JPanel();
        centralPane.add(day);
        centralPane.add(month);
        centralPane.add(year);
        centralPane.add(info);
        add(centralPane, BorderLayout.CENTER);
//        back.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try{
//                    Main.deadlines.add(new Deadline(info.getText(),
//                            new Date(Integer.parseInt(year.getText()) - YEAR_SHIFT,
//                                    Integer.parseInt(month.getText()),
//                                    Integer.parseInt(day.getText()))));
//                }catch(IllegalArgumentException exc){}
//                dispose();
//            }
//        });

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
            ADD_DIALOG_DIM = new Dimension(250,400),
            NAV_PANEL_DIM = new Dimension(250,50),
            DATE_FIELDS_DIM = new Dimension(70, 20),
            INFO_DIM = new Dimension(219, 280);
    private final String ADD_DIALOG_TITLE = "Add a deadline";
    // private final int YEAR_SHIFT = 1900;
}
