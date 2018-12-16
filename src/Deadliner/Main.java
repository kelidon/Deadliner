package Deadliner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main extends JDialog {

    private Main() {
        onCreate();

        timetable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainLayout.show(mainPane, String.valueOf(TIMETABLE_PANEL_INDEX));
            }
        });
        deadliner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainLayout.show(mainPane, String.valueOf(DEADLINES_PANEL_INDEX));
            }
        });
        alarms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO add alarms to the app
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCreate() {
        deadlines = new ArrayList<>();

        //menuIcon = new ImageIcon(MENU_PNG_FILEPATH);
        backIcon = new ImageIcon(BACK_PNG_FILEPATH);
        addIcon = new ImageIcon(ADD_PNG_FILEPATH);
        rightIcon = new ImageIcon(RIGHT_PNG_FILEPATH);
        leftIcon = new ImageIcon(LEFT_PNG_FILEPATH);
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
        mainPane.add(timetablePanel, String.valueOf(TIMETABLE_PANEL_INDEX));
        mainPane.add(deadlinesPanel, String.valueOf(DEADLINES_PANEL_INDEX));
        mainLayout.first(mainPane);

        add(mainPane, BorderLayout.CENTER);
        pack();
    }

    static String convertTime(String time){
        return time.replaceAll("<br>","-");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main dialog = new Main();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    private JPanel contentPane, mainPane;
    static Calendar calendar = Calendar.getInstance();

    static final int NUMBER_OF_CLASSES = 6,
            ICON_SIZE = 35;
    private final int
            TIMETABLE_PANEL_INDEX = 1,
            DEADLINES_PANEL_INDEX = 2;
    static final String[] CLASSES_TIME = {
                "08:15<br>09:35",
                "09:45<br>11:05",
                "11:15<br>12:35",
                "13:00<br>14:20",
                "14:20<br>15:50",
                "19:30<br>21:45"
    };
    static final String[] DAYS = {
            "Понедельник",
            "Вторник",
            "Среда",
            "Четверг",
            "Пятница",
            "Суббота"
    };
    private static final String FILE_PATH = "src/timetable.txt";
    private JMenuItem
            timetable,
            deadliner,
            alarms;
    static ArrayList<Deadline> deadlines;
    private CardLayout mainLayout;
    private final Dimension APP_DIMENSION = new Dimension(300, 500);
    static ImageIcon
            //menuIcon,
            backIcon,
            addIcon,
            rightIcon,
            leftIcon;
    private final String[] MENU_ITEMS_NAMES = {"Timetable", "Deadlines", "Alarms"};
    private  final String
            APP_TITLE = "Deadliner",
            BACK_PNG_FILEPATH = "image/back.png",
            ADD_PNG_FILEPATH = "image/add.png",
            MENU_PNG_FILEPATH = "image/menu.png",
            RIGHT_PNG_FILEPATH = "image/right.png",
            LEFT_PNG_FILEPATH = "image/left.png";
}
