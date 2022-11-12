import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Game extends JFrame{
    public Game(){
        this.setTitle("Word guessing");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImagePanel pan = new ImagePanel(new ImageIcon("img/background_2.jpg").getImage());
        this.setContentPane(pan);
        this.add(new MainPanel(),BorderLayout.CENTER);
        this.add(new Players(),BorderLayout.CENTER);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
}
