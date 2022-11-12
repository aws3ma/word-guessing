import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.BorderLayout;

class ImagePanel extends JPanel {

    private Image img;
  
    public ImagePanel(String img) {
      this(new ImageIcon(img).getImage());
    }
  
    public ImagePanel(Image img) {
      this.img = img;
      Dimension size = new Dimension(800, 600);
      setPreferredSize(size);
      setMinimumSize(size);
      setMaximumSize(size);
      setSize(size);
      setLayout(new BorderLayout());
    }
  
    public void paintComponent(Graphics g) {
      g.drawImage(img, 0, 0, null);
    }
  
}