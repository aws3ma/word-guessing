import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Players extends JPanel implements ActionListener {
    private int nbPlayer;
    private JButton add;
    private JButton confirm;
    private JButton cancel;
    private JTextField name;
    private JFrame addPlayer;
    DefaultListModel<String> model;

    public Players() {
        nbPlayer = 0;
        display();

    }

    public void display() {

        this.setOpaque(false);
        this.setSize(new Dimension(800, 600));
        this.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0 && j == 0) {
                    displayPlayersList();
                } else {
                    JPanel anyl = new JPanel();
                    anyl.setOpaque(false);
                    this.add(anyl);
                }
            }
        }
        this.setVisible(true);
    }

    public void displayPlayersList() {
        JPanel playerspan = new JPanel(new GridLayout(3, 1));
        playerspan.setOpaque(false);
        playerspan.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel playersLabel = new JLabel("Players");
        playersLabel.setFont(new Font("Serif", Font.BOLD, 18));

        playerspan.add(playersLabel);

        model = new DefaultListModel<>();
        JList<String> p = new JList<String>(model);
        p.setOpaque(false);
        p.setBackground(new Color(0, 0, 0, 0));
        playerspan.add(p);
        add = new JButton("+");
        add.setBackground(new Color(0, 123, 255));
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Serif", Font.BOLD, 18));
        add.addActionListener(this);
        playerspan.add(add);
        this.add(playerspan);
    }

    public void addPlayer() {
        addPlayer = new JFrame();
        addPlayer.setTitle("Add player");
        addPlayer.setPreferredSize(new Dimension(250, 100));
        addPlayer.setLocationRelativeTo(null);
        addPlayer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addPlayer.setResizable(false);
        addPlayer.setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(2, 1));
        form.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel buttons = new JPanel(new BorderLayout());
        JPanel input = new JPanel(new BorderLayout());
        name = new JTextField();
        name.setPreferredSize(new Dimension(180, 40));
        input.add(new JLabel("Name : "), BorderLayout.LINE_START);
        input.add(name, BorderLayout.LINE_END);
        form.add(input);
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        confirm.setBackground(new Color(4, 252, 132));
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBackground(new Color(220, 53, 69));
        buttons.add(confirm, BorderLayout.LINE_START);
        buttons.add(cancel, BorderLayout.LINE_END);
        form.add(buttons);
        addPlayer.add(form, BorderLayout.CENTER);
        addPlayer.pack();
        addPlayer.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            addPlayer();
        }
        if (e.getSource() == cancel) {
            addPlayer.setVisible(false);
        }
        if (e.getSource() == confirm) {
            addPlayer.setVisible(false);
            if (nbPlayer < 2) {
                model.addElement(name.getText());
                nbPlayer++;
                System.out.println(model.toArray()[0]);
            }
            if(nbPlayer==2){
                add.setVisible(false);
            }
        }

    }
}
