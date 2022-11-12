import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Players extends JPanel implements ActionListener{
    private ArrayList<String> players;
    private JButton add;
    public Players() {
        players = new ArrayList<>();
        players.add("Oussema");
        players.add("Aymen");
        this.setOpaque(false);
        this.setSize(new Dimension(800, 600));
        this.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0 && j == 0) {
                    JPanel playerspan = new JPanel(new GridLayout(players.size() + 2, 2));
                    playerspan.setOpaque(false);
                    playerspan.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                    JLabel playersLabel = new JLabel("Players");
                    playersLabel.setFont(new Font("Serif", Font.BOLD, 18));
                    playerspan.add(playersLabel);
                    JPanel anyl = new JPanel();
                    anyl.setOpaque(false);
                    playerspan.add(anyl);
                    for (String player : players) {
                        playerspan.add(new JLabel(player));
                        JPanel any = new JPanel();
                        any.setOpaque(false);
                        playerspan.add(any);

                    }
                    add = new JButton("+");
                    add.setBackground(new Color(0 ,123 ,255));
                    add.setForeground(Color.WHITE);
                    add.setFont(new Font("Serif", Font.BOLD, 18));
                    add.addActionListener(this);
                    playerspan.add(add);
                    this.add(playerspan);
                    JPanel any = new JPanel();
                    any.setOpaque(false);
                    playerspan.add(any);
                } else {
                    JPanel anyl = new JPanel();
                    anyl.setOpaque(false);
                    this.add(anyl);
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==add){
            new AddPlayer();
        }
        
    }
}
