import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainPanel extends JPanel implements ActionListener{
    private JButton play;
    private JButton exit;

    public MainPanel() {
        this.setOpaque(false);
        this.setSize(new Dimension(800, 600));
        this.setLayout(new GridLayout(4, 5));
        play = new JButton("Play");
        play.setBackground(new Color(40, 167, 69));
        play.setForeground(Color.white);
        play.setFont(new Font("Serif", Font.BOLD, 18));
        exit = new JButton("Exit");
        exit.setBackground(new Color(220, 53, 69));
        exit.addActionListener(this);
        exit.setForeground(Color.white);
        exit.setFont(new Font("Serif", Font.BOLD, 18));
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2, 1));
        buttons.add(play);
        buttons.add(exit);
        buttons.setOpaque(false);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 1 && j == 2) {
                    JLabel wordGuessing = new JLabel("Word guessing", SwingConstants.CENTER);
                    wordGuessing.setFont(new Font("Serif", Font.BOLD, 25));
                    this.add(wordGuessing);
                } else if (i == 2 && j == 2) {
                    this.add(buttons);
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
        if(e.getSource()==exit){
            System.exit(0);
        }
        
    }

}
