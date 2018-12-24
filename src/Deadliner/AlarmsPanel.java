package Deadliner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static Deadliner.Main.*;

public class AlarmsPanel extends JPanel {

    private class MyThread implements Runnable {
        private Thread myThread;

        MyThread(){
            myThread = new Thread(this, "Current Time Thread");
            myThread.start();
        }
        @Override
        public void run() {
            while (!isClosing) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM,    HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                currentTime.setText(now.format(dtf));
                try {
                    myThread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(this + "  stopped");
        }
    };

    AlarmsPanel(){
        super();
        // why 8?
        setLayout(new GridLayout(8,1));

        new MyThread();

        var panelForActuallAlarms = new JPanel(new GridBagLayout());
        add(panelForActuallAlarms);

        currentTime = new JLabel();
        panelForActuallAlarms.add(currentTime);

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/031100.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception exc){
            System.out.println(exc.getMessage());
        }

        var musicPanel = new JPanel();

        var playPause = new JRadioButton(playIcon);
        playPause.setSelectedIcon(pauseIcon);
        musicPanel.add(playPause);
        
        playPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip == null) {
                    System.out.println("audio file is not openned");
                }
                else {
                    if (playPause.isSelected())
                        clip.start();
                    else
                        clip.stop();
                }
            }
        });

        var linkButton = new JButton("Автор");
        musicPanel.add(linkButton);
        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://daitetank.ru/music/"));
                }catch (Exception exc){
                    exc.printStackTrace();
                }
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
                    playPause.setSelected(true);
                    clip.start();
                }
            }
        };

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!timerField.getText().equals("")) {
                    var timer = new Timer(Integer.parseInt(timerField.getText())*1000, timerListener);
                    timer.start();
                    timer.setRepeats(false);
                }
            }
        });
        add(musicPanel);
        add(timerPanel);
    }

    public void killClipOnClose(){
        clip.stop();
        clip.close();
    }

    private JLabel currentTime;
    private Clip clip;
    private Dimension TIMER_DIM = new Dimension(30,27);
}
