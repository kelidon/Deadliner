package Deadliner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeadlinesPanel extends JPanel{
    private TextArea deadlinesArea = new TextArea();
    DeadlinesPanel(){
        this.setLayout(new BorderLayout());

        var centralPane = new JPanel();
        centralPane.add(deadlinesArea);
        this.add(centralPane, BorderLayout.CENTER);
        this.showDeadlines();

        var navPanel = new JPanel();
        navPanel.setPreferredSize(new Dimension(350,45));

        Main.addIcon = new ImageIcon(Main.addIcon.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH));
        var add = new JRadioButton(Main.addIcon);

        navPanel.add(add);
        this.add(navPanel, BorderLayout.NORTH);

        deadlinesArea.setEditable(false);
        deadlinesArea.setPreferredSize(new Dimension(280,350));

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var addDeadline = new AddDeadlineDialog();
                addDeadline.pack();
                addDeadline.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                addDeadline.setVisible(true);
                showDeadlines();
            }
        });
    }
    private void showDeadlines(){
        deadlinesArea.setText("");
        if(!Main.deadlines.isEmpty()){
            for(var deadline: Main.deadlines){
                deadlinesArea.append(deadline.getInfo() +
                        "\t" +
                        String.format("%te %<tB %<tY", deadline.getDeadlineDate()) +
                        "\n");
            }
        }
    }

}
