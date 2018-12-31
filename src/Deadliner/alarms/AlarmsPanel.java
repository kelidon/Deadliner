package deadliner.alarms;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static deadliner.Main.*;

public class AlarmsPanel extends JPanel {

    private class AlarmThread implements Runnable {
        private Thread myThread;

        AlarmThread(){
            myThread = new Thread(this, "Current Time Thread");
            myThread.start();
        }
        @Override
        public void run() {
            while (!isClosing) {
                updateCurrentTime();
                if (isAlarmSet){
                    updateTimeUntilAlarm();
                }
            }
            System.out.println(this + "  stopped");
        }

        private void updateCurrentTime(){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM,    HH:mm:ss");
            now = LocalDateTime.now();
            currentTime.setText(now.format(dtf));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        private void updateTimeUntilAlarm(){
            var timeLeft =  alarmTime.minusHours(now.getHour())
                                    .minusMinutes(now.getMinute())
                                    .minusSeconds(now.getSecond() + 1);
            alarmTimeLeftLabel.setText(
                    timeLeft.format( DateTimeFormatter.ofPattern("HH:mm:ss") )
            );
            if (timeLeft.getHour() == 0 && timeLeft.getMinute() == 0 && timeLeft.getSecond() == 0){
                isAlarmSet = false;
                playPauseButton.setSelected(true);
                clip.start();
                alarmTimeLeftLabel.setText("Rang!");
            }
        }
    }

    private void setActualAlarmsPanel(){
        var panelForActualAlarms = new JPanel(new GridLayout(0, 1));

        currentTime = new JLabel();

        var setAlarmPanel = new JPanel();

        ActionListener setAlarmTimeListener = e -> {
            //пойдут, возможно, костыли. Пролема в том, что дата из строки требует год. Вот его и добавим
            String hoursMinutes = alarmTimeField.getText();
            hoursMinutes += "-00 11-11-2099";

            try {
                alarmTime = LocalDateTime.parse(
                        hoursMinutes,
                        DateTimeFormatter.ofPattern("H[H]-m[m]-ss dd-MM-yyyy")
                );
                isAlarmSet = true;
            } catch (Exception exc) {
                isAlarmSet = false;
                JOptionPane.showMessageDialog(
                        null,
                        "invalid format for alarm\n" +
                        "correct format : hh-mm"
                );
            }
        };

        alarmTimeField = new JTextField("hh-mm");
        alarmTimeField.setPreferredSize( ALARM_SIZE );
        alarmTimeField.addActionListener(setAlarmTimeListener);

        JButton setAlarmButton = new JButton("set");
        setAlarmButton.addActionListener(setAlarmTimeListener);

        JButton cancelAlarmButton = new JButton("cancel");
        cancelAlarmButton.addActionListener( e -> {
            if (isAlarmSet) {
                isAlarmSet = false;
                alarmTimeLeftLabel.setText("alarm canceled");
            }
        });

        alarmTimeLeftLabel = new JLabel("Time until alarm");

        setAlarmPanel.add(alarmTimeField);
        setAlarmPanel.add(setAlarmButton);
        setAlarmPanel.add(cancelAlarmButton);
        setAlarmPanel.add(alarmTimeLeftLabel);

        panelForActualAlarms.add(currentTime);
        panelForActualAlarms.add(setAlarmPanel);
        add(panelForActualAlarms);
    }

    public AlarmsPanel(){
        super();
        // why 6?
        setLayout(new GridLayout(6,1));

        setActualAlarmsPanel();

        new AlarmThread();

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    new File("src/031100.wav").getAbsoluteFile()
            );
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception exc){
            System.out.println(exc.getMessage());
        }

        var musicPanel = new JPanel();

        playPauseButton = new JRadioButton(playIcon);
        playPauseButton.setSelectedIcon(pauseIcon);
        musicPanel.add(playPauseButton);
        
        playPauseButton.addActionListener(e -> {
            if (clip == null) {
                System.out.println("audio file is not openned");
            }
            else {
                if (playPauseButton.isSelected())
                    clip.start();
                else
                    clip.stop();
            }
        });

        var linkButton = new JButton("Автор");
        musicPanel.add(linkButton);
        linkButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://daitetank.ru/music/"));
            }catch (Exception exc){
                exc.printStackTrace();
            }
        });

        var timerPanel = new JPanel();
        var timerField = new JTextField();
        timerField.setPreferredSize(TIMER_DIM);
        timerPanel.add(timerField);
        var submitButton = new JButton("Submit");
        timerPanel.add(submitButton);

        var timerListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip == null) {
                    System.out.println("audio file is not openned");
                }
                else {
                    playPauseButton.setSelected(true);
                    clip.start();
                }
            }
        };

        submitButton.addActionListener(e -> {
            if(!timerField.getText().equals("")) {
                var timer = new Timer(Integer.parseInt(timerField.getText())*1000, timerListener);
                timer.start();
                timer.setRepeats(false);
            }
        });
        add(musicPanel);
        add(timerPanel);
    }

    public void killClipOnClose(){
        clip.stop();
        clip.close();
    }

    private boolean isAlarmSet = false;
    private LocalDateTime now;
    private LocalDateTime alarmTime;
    private JLabel alarmTimeLeftLabel;
    private JTextField alarmTimeField;
    private JLabel currentTime;

    private JRadioButton playPauseButton;
    private Clip clip;
    private Dimension TIMER_DIM = new Dimension(30,27);
    private Dimension ALARM_SIZE = new Dimension(50, 27);
}
