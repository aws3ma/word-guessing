import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class JMultilineLabel extends JTextArea {
    private static final long serialVersionUID = 1L;

    public JMultilineLabel(String text) {
        super(text); // According to Mr. Polywhirl, this might improve it -> text +
                     // System.lineSeparator()
        setEditable(false);
        setCursor(null);
        setOpaque(false);
        setFocusable(false);
        setFont(new Font("Serif", Font.PLAIN, 18));
        setWrapStyleWord(true);
        setLineWrap(true);
        // According to Mariana this might improve it
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setAlignmentY(JLabel.CENTER_ALIGNMENT);
        setAlignmentX(JLabel.CENTER_ALIGNMENT);
    }
}