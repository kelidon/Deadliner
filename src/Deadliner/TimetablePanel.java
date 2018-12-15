package Deadliner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

import static Deadliner.Main.NUMBER_OF_CLASSES;

public class TimetablePanel extends JPanel {

    public TimetablePanel(String timetableFilePath) {

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
        turnButtonSPanel.setLayout(new GridLayout(1, 2));
        turnButtonSPanel.setPreferredSize(new Dimension(300, 50));
        var next = new Button(">");
        var previous = new Button("<");
        turnButtonSPanel.add(previous);
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
            }
        });
    }
    static int viewedDayNumber;
    static Week week;
}
