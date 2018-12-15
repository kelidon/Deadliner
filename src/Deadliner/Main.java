package Deadliner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Calendar;

public class Main extends JDialog {
    static ImageIcon menuIcon = new ImageIcon("image/menu.png"),
            backIcon = new ImageIcon("image/back.png"),
            addIcon = new ImageIcon("image/add.png");
    static Week week;

    class DayPanel extends JPanel implements MouseListener {
        private SubjectButton[] subjectButtons = new SubjectButton[6];
        private NoteDialog noteDialog;

        DayPanel(Week week, int day) {
            setLayout(new GridLayout(NUMBER_OF_CLASSES, 1));
            setPreferredSize(new Dimension(300, 500));
            for (int i = 0; i < NUMBER_OF_CLASSES; i++) {
                subjectButtons[i] = new SubjectButton(week.classes[day][i], i, day);
                add(subjectButtons[i]);
                subjectButtons[i].addMouseListener(this);
                
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //if (SwingUtilities.isLeftMouseButton(e)) {
            SubjectButton clicked = ((SubjectButton) e.getSource());
            int subjectIndex = clicked.getIndex();
            noteDialog = new NoteDialog(subjectIndex);
            noteDialog.pack();
            noteDialog.setModalityType(ModalityType.APPLICATION_MODAL);
            noteDialog.setVisible(true);
            //} else if (SwingUtilities.isRightMouseButton(e)) {

            //}
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private Main() {


        onCreate();

        timetable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deadliner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var deadlinesDialog = new DeadlinesDialog();
                deadlinesDialog.pack();
                deadlinesDialog.setModalityType(ModalityType.APPLICATION_MODAL);
                deadlinesDialog.setVisible(true);
            }
        });
        showMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(menuPanel, BorderLayout.WEST);
            }
        });
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(viewedDayNumber == 5) {
                    viewedDayNumber = 0;
                    layout.first(timetablePanel);
                }
                else {
                    viewedDayNumber++;
                    layout.next(timetablePanel);
                }
                pack();
            }
        });
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(viewedDayNumber==0){
                    viewedDayNumber=5;
                    layout.last(timetablePanel);
                }
                else{
                    viewedDayNumber--;
                    layout.previous(timetablePanel);
                }
                pack();
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCreate() {
        setContentPane(contentPane);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        setPreferredSize(new Dimension(400, 600));
        setLayout(new BorderLayout());

        week = new Week(new File(FILE_PATH));
        dayPanels = new DayPanel[6];
        for (int i = 0; i < NUMBER_OF_CLASSES; i++) {
            dayPanels[i] = new DayPanel(week, i);
        }

        layout = new CardLayout();
        timetablePanel = new JPanel(layout);
        for(int i = 0;i < NUMBER_OF_CLASSES;i++)
        {
            timetablePanel.add(dayPanels[i], String.valueOf(i));
        }
        add(timetablePanel, BorderLayout.CENTER);

        if (dayOfTheWeek == 1) {
            layout.first(timetablePanel);
            viewedDayNumber = 0;
        } else {
            layout.show(timetablePanel, String.valueOf(dayOfTheWeek - 2));
            viewedDayNumber = dayOfTheWeek - 2;
        }

        var turnButtonSPanel = new JPanel();
        turnButtonSPanel.setLayout(new GridLayout(1, 2));
        turnButtonSPanel.setPreferredSize(new Dimension(300, 50));
        next = new Button(">");
        previous = new Button("<");
        turnButtonSPanel.add(previous);
        turnButtonSPanel.add(next);
        add(turnButtonSPanel, BorderLayout.SOUTH);

        var navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1, 5));
        navPanel.setPreferredSize(new Dimension(300, 35));
        menuIcon = new ImageIcon(menuIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        showMenu = new JRadioButton(menuIcon);
        navPanel.add(showMenu);
        add(navPanel, BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(8, 1));
        menuPanel.setPreferredSize(new Dimension(80, 600));
        timetable = new Button("Timetable");
        deadliner = new Button("Deadliner");
        menuPanel.add(timetable);
        menuPanel.add(deadliner);
        add(menuPanel, BorderLayout.WEST);

        pack();
    }

    private void onOK() {
        //TODO add your code here
        dispose();
    }

    private void onCancel() {
        //TODO add your code here if necessary
        dispose();
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

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private Calendar calendar = Calendar.getInstance();
    private int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
    static final int NUMBER_OF_CLASSES = 6;
    static final String[] CLASSES_TIME = {"08:15-09:35", "09:45-11:05", "11:15-12:35", "13:00-14:20", "14:20-15:50", "19:30-21:45"};
    private static final String FILE_PATH = "src/timetable.txt";
    static int viewedDayNumber;
    private DayPanel[] dayPanels;
    private Button next, previous, timetable, deadliner;
    private JRadioButton showMenu;
    private JPanel menuPanel, timetablePanel;
    private CardLayout layout;
}
