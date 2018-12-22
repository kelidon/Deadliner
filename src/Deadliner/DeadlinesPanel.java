package Deadliner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static Deadliner.Main.ICON_SIZE;

class DeadlinesPanel extends JPanel implements MouseListener {

    DeadlinesPanel(ArrayList<Deadline> deadlines){
        deadlines.add(new Deadline("Test deadline"));
        this.setLayout(new BorderLayout());
        centralPane = new JPanel();
        centralPane.setLayout(new GridLayout(100,1));
        for(int i=0;i<10;i++) {
            deadButtons[i]= new JButton();
            deadButtons[i].addMouseListener(this);
            if(i+1<=deadlines.size()) {
                deadButtons[i].setText(deadlines.get(i).getInfo());
                deadButtons[i].setBorder(new RoundedBorder(25,2,Color.white));
            }
            centralPane.add(deadButtons[i]);
        }
        this.add(centralPane, BorderLayout.CENTER);

        var navPanel = new JPanel();
        navPanel.setPreferredSize(NAV_PANEL_DIM);

        var add = new JRadioButton(Main.addIcon);
        navPanel.add(add);
        this.add(navPanel, BorderLayout.SOUTH);


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var addDeadline = new AddDeadlineDialog(deadlines);
                addDeadline.pack();
                addDeadline.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                addDeadline.setVisible(true);
                deadButtons[deadlines.size()-1].setText(deadlines.get(deadlines.size()-1).getInfo());
                deadButtons[deadlines.size()-1].setBorder(new RoundedBorder(25,2,Color.white));
            }
        });
    }
//    private void showDeadlines(){
//        if(!Main.deadlines.isEmpty()){
//            for(int i=0;i<deadlines.size();i++){
//                var deadButton = new JButton(deadlines.get(i).getInfo());
//                deadButton.setBorder(new RoundedBorder(25,2,Color.white));
//            }
//        }
//    }
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    JPanel centralPane;
    JButton[] deadButtons = new JButton[10];
    private final Dimension
            NAV_PANEL_DIM = new Dimension(350,50),
            DEADLINES_AREA_DIM = new Dimension(280,380);
}
