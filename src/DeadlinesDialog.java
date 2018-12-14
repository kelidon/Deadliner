import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

public class DeadlinesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton back, add;
    static HashSet<Deadline> deadlines = new HashSet<>();

    public DeadlinesDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLayout(new BorderLayout());

        var scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        if(!deadlines.isEmpty()){
            for(var deadline: deadlines){
                add(new TextField(deadline.getInfo()+"\t"+ deadline.getDeadlineDate().toString()));
            }
        }

        var navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1,5));
        navPanel.setPreferredSize(new Dimension(300,35));

        Main.backIcon = new ImageIcon(Main.backIcon.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH));
        back = new JRadioButton(Main.backIcon);
        navPanel.add(back);

        Main.addIcon = new ImageIcon(Main.addIcon.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH));
        add = new JRadioButton(Main.addIcon);
        navPanel.add(add);
        add(navPanel, BorderLayout.NORTH);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var addDeadline = new AddDeadlineDialog();
                addDeadline.pack();
                addDeadline.setModalityType(ModalityType.APPLICATION_MODAL);
                addDeadline.setVisible(true);
                if(!deadlines.isEmpty()){
                    for(var deadline: deadlines){
                        add(new TextField(deadline.getInfo()+"\t"+ deadline.getDeadlineDate().toString()));
                    }
                }
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

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        DeadlinesDialog dialog = new DeadlinesDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

