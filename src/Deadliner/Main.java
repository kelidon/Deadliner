package Deadliner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Main extends JDialog {
    static ImageIcon
            //menuIcon = new ImageIcon("image/menu.png"),
            backIcon = new ImageIcon("image/back.png"),
            addIcon = new ImageIcon("image/add.png");

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

        setTitle("Deadliner");
        setContentPane(contentPane);

        mainLayout = new CardLayout();
        mainPane = new JPanel();
        mainPane.setLayout(mainLayout);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setPreferredSize(new Dimension(320, 500));
        setLayout(new BorderLayout());

        var menuBar = new JMenuBar();
        timetable = new JMenuItem("Timetable");
        deadliner = new JMenuItem("Deadlines");
        alarms = new JMenuItem("Alarms");
        menuBar.add(timetable);
        menuBar.add(deadliner);
        menuBar.add(alarms);
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

    static final int NUMBER_OF_CLASSES = 6;
    private final int
            TIMETABLE_PANEL_INDEX = 1,
            DEADLINES_PANEL_INDEX = 2;
    static final String[] CLASSES_TIME = {"08:15-09:35", "09:45-11:05", "11:15-12:35", "13:00-14:20", "14:20-15:50", "19:30-21:45"};
    private static final String FILE_PATH = "src/timetable.txt";
    private JMenuItem
            timetable,
            deadliner,
            alarms;
    static ArrayList<Deadline> deadlines;
    private CardLayout mainLayout;
}
