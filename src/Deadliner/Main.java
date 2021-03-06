package deadliner;

import deadliner.alarms.AlarmsPanel;
import deadliner.deadlines.Deadline;
import deadliner.deadlines.DeadlinesPanel;
import deadliner.timetable.TimetablePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main extends JDialog {

    private Main() {
        contentPane = new JPanel();
        onCreate();

        timetable.addActionListener(e -> mainLayout.show(mainPane, String.valueOf(TIMETABLE_PANEL_INDEX)));
        deadliner.addActionListener(e -> mainLayout.show(mainPane, String.valueOf(DEADLINES_PANEL_INDEX)));
        alarms.addActionListener(e -> mainLayout.show(mainPane, String.valueOf(ALARMS_PANEL_INDEX)));

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                alarmsPanel.killClipOnClose();

                isClosing = true;

                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(
                e -> {
                    isClosing = true;
                    alarmsPanel.killClipOnClose();
                    dispose();
                },
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    @FunctionalInterface
    public interface Foo {
        ImageIcon method(String string);
    }

    private void onCreate() {
        deadlines = new ArrayList<>();

        try {
//            TODO : replace all this mess with local lambda. That will be amazing/
            // Foo getIcon = path -> {
            //         try {
            //         return new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(BACK_PNG_FILEPATH)));
            //     } catch (Exception e) {
                    
            //     }
            // };
        //    menuIcon = new ImageIcon( ImageIO.read( this.getClass().getResourceAsStream(MENU_PNG_FILEPATH)));
            backIcon = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(BACK_PNG_FILEPATH)));
            addIcon = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(ADD_PNG_FILEPATH)));
            rightIcon = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(RIGHT_PNG_FILEPATH)));
            leftIcon = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(LEFT_PNG_FILEPATH)));
            playIcon = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(PLAY_PNG_FILEPATH)));
            pauseIcon = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(PAUSE_PNG_FILEPATH)));
            httpIcon = new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(HTTP_PNG_FILEPATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle(APP_TITLE);
        setContentPane(contentPane);

        mainLayout = new CardLayout();
        mainPane = new JPanel();
        mainPane.setLayout(mainLayout);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setPreferredSize(APP_DIMENSION);
        setLayout(new BorderLayout());

        var menuBar = new JMenuBar();
        timetable = new JMenuItem(MENU_ITEMS_NAMES[0]);
        deadliner = new JMenuItem(MENU_ITEMS_NAMES[1]);
        alarms = new JMenuItem(MENU_ITEMS_NAMES[2]);
        var day = new JMenuItem(new SimpleDateFormat("EEE").format(new Date()));
        //day.setBackground(Color.darkGray);
        menuBar.add(timetable);
        menuBar.add(deadliner);
        menuBar.add(alarms);
        menuBar.add(day);
        timetable.setMnemonic(KeyEvent.VK_T);
        deadliner.setMnemonic(KeyEvent.VK_D);
        alarms.setMnemonic(KeyEvent.VK_A);
        setJMenuBar(menuBar);

        var timetablePanel = new TimetablePanel(FILE_PATH);
        var deadlinesPanel = new DeadlinesPanel();
        alarmsPanel = new AlarmsPanel();
        mainPane.add(timetablePanel, String.valueOf(TIMETABLE_PANEL_INDEX));
        mainPane.add(deadlinesPanel, String.valueOf(DEADLINES_PANEL_INDEX));
        mainPane.add(alarmsPanel, String.valueOf(ALARMS_PANEL_INDEX));
        mainLayout.first(mainPane);

        add(mainPane, BorderLayout.CENTER);
        pack();
    }

    public static String convertTime(String time) {
        return time.replaceAll("<br>", "-");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main dialog = new Main();
            dialog.pack();
            dialog.setVisible(true);
        });
    }

    public static boolean isClosing = false;
    private JPanel contentPane, mainPane;
    public static Calendar calendar = Calendar.getInstance();

    public static final int NUMBER_OF_CLASSES = 6,
            ICON_SIZE = 35;
    public static final String[] CLASSES_TIME = {
            "08:15<br>09:35",
            "09:45<br>11:05",
            "11:15<br>12:35",
            "13:00<br>14:20",
            "14:20<br>15:50",
            "19:30<br>21:45"
    };
    public static final String[] DAYS = {
            "Понедельник",
            "Вторник",
            "Среда",
            "Четверг",
            "Пятница",
            "Суббота"
    };
    // TODO: remove all occurrences of "/resources"
    // but it may (and will) break your IDEA. 
    // I mean it won't run and your project
    // So you will have to run it manually from command line (or smth else)
    // A least that what happened to mee, and I still have no idea how to fix it
    private static final String FILE_PATH = "/resources/timetable/timetable.txt";
    private JMenuItem
            timetable,
            deadliner,
            alarms;
    public static ArrayList<Deadline> deadlines;
    private CardLayout mainLayout;
    private AlarmsPanel alarmsPanel;
    private final Dimension APP_DIMENSION = new Dimension(300, 500);
    public static ImageIcon
            // menuIcon,
            backIcon,
            addIcon,
            rightIcon,
            leftIcon,
            pauseIcon,
            playIcon,
            httpIcon;
    private static final int
            TIMETABLE_PANEL_INDEX = 1,
            DEADLINES_PANEL_INDEX = 2,
            ALARMS_PANEL_INDEX = 3;
    private static final String[] MENU_ITEMS_NAMES = {"Timetable", "Deadlines", "Alarms"};
    private static final String
            APP_TITLE = "deadliner",
            // MENU_PNG_FILEPATH = "/resources/icons/menu.png",
            BACK_PNG_FILEPATH = "/resources/icons/back.png",
            ADD_PNG_FILEPATH = "/resources/icons/add.png",
            RIGHT_PNG_FILEPATH = "/resources/icons/right.png",
            LEFT_PNG_FILEPATH = "/resources/icons/left.png",
            PLAY_PNG_FILEPATH = "/resources/icons/play.png",
            PAUSE_PNG_FILEPATH = "/resources/icons/pause.png",
            HTTP_PNG_FILEPATH = "/resources/icons/http.png";
}
