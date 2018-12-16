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

import static Deadliner.Main.*;

public class AlarmsPanel extends JPanel {

    AlarmsPanel(){
        super();
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/031100.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception exc){
            System.out.println(exc.getMessage());
        }

        var playPause = new JRadioButton(playIcon);
        playPause.setSelectedIcon(pauseIcon);
        playPause.setSelected(true);
        add(playPause);
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
        var linkButton = new JRadioButton(httpIcon);
        add(linkButton);
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

    }
    private Clip clip;
}
