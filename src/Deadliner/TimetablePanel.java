package Deadliner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.DayOfWeek;
import java.util.Calendar;

import static Deadliner.Main.NUMBER_OF_CLASSES;

class TimetablePanel extends JPanel {

    TimetablePanel(String timetableFilePath) {

        this.setLayout(new BorderLayout());

        week = new Week(new File(timetableFilePath));
        var dayPanels = new DayPanel[6];
        for (int i = 0; i < NUMBER_OF_CLASSES; i++) {
            dayPanels[i] = new DayPanel(week, i);
        }

        var layout = new CardLayout();
        var subjectSPane = new JPanel(layout);
        for(int i = 0;i < NUMBER_OF_CLASSES;i++)
        {
            subjectSPane.add(dayPanels[i], String.valueOf(i));
        }
        this.add(subjectSPane, BorderLayout.CENTER);

        int dayOfTheWeek = Main.calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfTheWeek == 1) {
            layout.first(subjectSPane);
            viewedDayNumber = 0;
        } else {
            layout.show(subjectSPane, String.valueOf(dayOfTheWeek - 2));
            viewedDayNumber = dayOfTheWeek - 2;
        }

        var turnButtonSPanel = new JPanel();

        turnButtonSPanel.setPreferredSize(TURN_DIM);
        turnButtonSPanel.setLayout(new GridLayout(1,3));

        var next = new JButton(Main.rightIcon);
        next.setOpaque(false);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);

        var previous = new JButton(Main.leftIcon);
        previous.setOpaque(false);
        previous.setContentAreaFilled(false);
        previous.setBorderPainted(false);

        var dayOfWeekLabel = new JLabel( DayOfWeek.of(viewedDayNumber + 1).toString() );

        turnButtonSPanel.add(previous);
        turnButtonSPanel.add(dayOfWeekLabel);
        turnButtonSPanel.add(next);

        this.add(turnButtonSPanel, BorderLayout.SOUTH);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(viewedDayNumber == 5) {
                    viewedDayNumber = 0;
                    layout.first(subjectSPane);
                }
                else {
                    viewedDayNumber++;
                    layout.next(subjectSPane);
                }
                dayOfWeekLabel.setText( DayOfWeek.of(viewedDayNumber + 1).toString() );
            }
        });
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(viewedDayNumber==0){
                    viewedDayNumber=5;
                    layout.last(subjectSPane);
                }
                else{
                    viewedDayNumber--;
                    layout.previous(subjectSPane);
                }
                dayOfWeekLabel.setText( DayOfWeek.of(viewedDayNumber + 1).toString() );
            }
        });
    }
    static int viewedDayNumber;
    static Week week;
    private final Dimension TURN_DIM = new Dimension(300, 50);
}
