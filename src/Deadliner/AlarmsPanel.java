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

public class AlarmsPanel extends JPanel {
    private Clip clip;
    AlarmsPanel(){
        super();

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/031100.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception exc){
            System.out.println(exc.getMessage());
        }

        JButton button = new JButton("play");
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip == null){
                    System.out.println("audio file is not openned");
                    return;
                }
                clip.start();
            }
        });
        button = new JButton("stop");
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip == null){
                    System.out.println("audio file is not openned");
                    return;
                }
                clip.stop();
            }
        });

        button = new JButton("music author");
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://daitetank.ru/music/"));
                }catch (Exception exc){
                    exc.printStackTrace();
                }
            }
        });
    }
}
