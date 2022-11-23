import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainMenu extends JFrame implements ActionListener {
    private JButton play;
    private Players p;

    public MainMenu() {
        this.setTitle("Word guessing");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImagePanel pan = new ImagePanel(new ImageIcon("img/background_2.jpg").getImage());
        this.setContentPane(pan);
        play = new JButton("Play");
        play.setBackground(new Color(40, 167, 69));
        play.setForeground(Color.white);
        play.setFont(new Font("Serif", Font.BOLD, 18));
        play.addActionListener(this);
        p = new Players(play);
        this.add(new MainPanel(play), BorderLayout.CENTER);
        this.add(p, BorderLayout.CENTER);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            if (p.getNbPlayer() == 2) {
                Game g = new Game(Arrays.copyOf(Players.model.toArray(), 2, String[].class), this);
                this.add(g);
            }
        }

    }
}
