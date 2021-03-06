package deadliner.timetable;

import deadliner.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

import static deadliner.Main.DAYS;
import static deadliner.Main.NUMBER_OF_CLASSES;

public class TimetablePanel extends JPanel {

    public TimetablePanel(String timetableFilePath) {

        this.setLayout(new BorderLayout());

        week = new Week(timetableFilePath);
        var dayPanels = new DayPanel[6];
        for (int i = 0; i < NUMBER_OF_CLASSES; i++) {
            dayPanels[i] = new DayPanel(week, i);
        }

        var layout = new CardLayout();
        var dayPanel = new JPanel(layout);
        for(int i = 0;i < NUMBER_OF_CLASSES;i++)
        {
            dayPanel.add(dayPanels[i], String.valueOf(i));
        }
        this.add(dayPanel, BorderLayout.CENTER);

        int dayOfTheWeek = Main.calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfTheWeek == 1) {
            layout.first(dayPanel);
            viewedDayNumber = 0;
        } else {
            layout.show(dayPanel, String.valueOf(dayOfTheWeek - 2));
            viewedDayNumber = dayOfTheWeek - 2;
        }


        var currDayPanel = new JPanel();
        currDayPanel.setBackground(Color.white);
        currDayPanel.setBorder(new RoundedBorder(0,0,Color.white, Color.white));

        dayLabel = new JLabel(DAYS[viewedDayNumber]);
        dayLabel.setBackground(Color.white);
        currDayPanel.add(dayLabel);
        this.add(currDayPanel, BorderLayout.NORTH);

        var turnButtonSPanel = new JPanel();
        turnButtonSPanel.setPreferredSize(TURN_DIM);
        turnButtonSPanel.setLayout(new GridLayout(1,2));

        var next = new JButton(Main.rightIcon);
        next.setOpaque(false);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);

        var previous = new JButton(Main.leftIcon);
        previous.setOpaque(false);
        previous.setContentAreaFilled(false);
        previous.setBorderPainted(false);


        turnButtonSPanel.add(previous);
        turnButtonSPanel.add(next);

        this.add(turnButtonSPanel, BorderLayout.SOUTH);

        next.addActionListener(e -> {
            if(viewedDayNumber == 5) {
                viewedDayNumber = 0;
                layout.first(dayPanel);
            }
            else {
                viewedDayNumber++;
                layout.next(dayPanel);
            }
            dayLabel.setText(DAYS[viewedDayNumber]);
        });
        previous.addActionListener(e -> {
            if(viewedDayNumber==0){
                viewedDayNumber=5;
                layout.last(dayPanel);
            }
            else{
                viewedDayNumber--;
                layout.previous(dayPanel);
            }
            dayLabel.setText(DAYS[viewedDayNumber]);
        });
    }
    static int viewedDayNumber;
    static Week week;
    private static final Dimension TURN_DIM = new Dimension(300, 50);
    private JLabel dayLabel;
}
