package deadliner.deadlines;

import deadliner.Main;

import javax.swing.*;
import java.awt.*;

public class DeadlinesPanel extends JPanel{

    public DeadlinesPanel(){
        this.setLayout(new BorderLayout());
        deadlinesArea = new TextArea();
        var centralPane = new JPanel();
        centralPane.add(deadlinesArea);
        this.add(centralPane, BorderLayout.CENTER);
        this.showDeadlines();

        var navPanel = new JPanel();
        navPanel.setPreferredSize(NAV_PANEL_DIM);

        var add = new JRadioButton(Main.addIcon);
        navPanel.add(add);
        this.add(navPanel, BorderLayout.SOUTH);

        deadlinesArea.setEditable(false);
        deadlinesArea.setPreferredSize(DEADLINES_AREA_DIM);

        add.addActionListener(e -> {
            var addDeadline = new AddDeadlineDialog();
            addDeadline.pack();
            addDeadline.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            addDeadline.setVisible(true);
            showDeadlines();
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

    private TextArea deadlinesArea;
    private static final Dimension
            NAV_PANEL_DIM = new Dimension(350,50),
            DEADLINES_AREA_DIM = new Dimension(280,380);
}
