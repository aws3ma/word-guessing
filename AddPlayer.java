import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
public class AddPlayer extends JFrame implements ActionListener{
    private JButton confirm;
    private JButton cancel;
    private JTextField name;
    public AddPlayer(){
        this.setTitle("Add player");
        this.setPreferredSize(new Dimension(250,100));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(2,1));
        form.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel buttons = new JPanel(new BorderLayout());
        JPanel input = new JPanel(new BorderLayout());
        name = new JTextField();
        name.setPreferredSize(new Dimension(180,40));
        input.add(new JLabel("Name : "),BorderLayout.LINE_START);
        input.add(name,BorderLayout.LINE_END);
        form.add(input);
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        confirm.setBackground(new Color(4, 252, 132));
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBackground(new Color(220, 53, 69));
        buttons.add(confirm,BorderLayout.LINE_START);
        buttons.add(cancel,BorderLayout.LINE_END);
        form.add(buttons);
        this.add(form,BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancel){
           this.setVisible(false);
        }
        if(e.getSource()==confirm){

        }
        
    }
}
