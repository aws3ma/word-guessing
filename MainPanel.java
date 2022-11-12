import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Font;
public class MainPanel extends JPanel {
    public MainPanel() {
        this.setOpaque(false);
        this.setSize(new Dimension(800, 600));
        this.setLayout(new GridLayout(4,5));
        JButton play = new JButton("Play");
        JButton exit = new JButton("Exit");
        play.setMargin(new Insets(20, 20, 20, 20));
        exit.setMargin(new Insets(20, 20, 20, 20));
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2,1));
        buttons.add(play);
        buttons.add(exit);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if(i==1 && j==2){
                    JLabel wordGuessing = new JLabel("Word guessing",SwingConstants.CENTER);
                    wordGuessing.setFont(new Font("Serif", Font.BOLD, 25));
                    this.add(wordGuessing);
                }else if(i==2 && j==2){
                    this.add(buttons);
                }else{
                    JPanel anyl = new JPanel();
                    anyl.setOpaque(false);
                    this.add(anyl);
                }
            }
        }
    }

}
