import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Game extends JPanel implements ActionListener,KeyListener {
    private String[] players;
    private Map<String, String> quests;
    private Map<Integer, Character> cryptedWord;
    private DefaultListModel<Integer> score;
    private JList<String> p;
    private JList<Integer> s;
    private JLabel currentPlayer;
    // private JLabel question;
    private JMultilineLabel question;
    private JTextField answer;
    private JButton valider;
    private JButton homeButton;
    private JFrame window;
    private String ques, ans;
    private List<String> keylist;
    private Random generator;
    private int i = 1;
    private int scorep2 = 0;
    private int scorep1 = 0;
    private JPanel answerPanel;
    private JPanel JPans;
    private GridLayout gr;
    public Game(String[] players, JFrame f) {
        window = f;
        quests = new HashMap<>();
        this.players = players;
        this.setOpaque(false);
        this.setSize(new Dimension(800, 600));
        this.setLayout(new GridLayout(5, 3));
        try {
            readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        generator = new Random();
        keylist = new ArrayList<>(quests.keySet());
        ques = keylist.get(generator.nextInt(keylist.size()));
        ans = quests.get(ques);
        quests.remove(ques);
        question = new JMultilineLabel("Question");

        question.setText(ques);
        // cryptedWord = new HashMap<>();
        cryptedWord = getCyptedWord();
        display();
        this.setVisible(true);
        // cryptedWord.entrySet().forEach(entry -> {
        // System.out.println(entry.getKey() + " " + entry.getValue());
        // });
        currentPlayer.setText(players[0]);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    public void display() {
        JPanel scoreboard = new JPanel(new GridLayout(2, 3));
        scoreboard.setOpaque(false);
        score = new DefaultListModel<>();
        p = new JList<>(players);
        score.add(0, 0);
        score.add(1, 0);
        s = new JList<>(score);
        p.setBackground(new Color(0, 0, 0, 0));
        s.setBackground(new Color(0, 0, 0, 0));

        scoreboard.add(new JLabel("Player"));
        scoreboard.add(new JLabel("Score"));
        scoreboard.add(new JLabel());
        scoreboard.add(p);
        scoreboard.add(s);
        scoreboard.add(new JLabel());
        scoreboard.setPreferredSize(new Dimension(200, 400));
        this.add(scoreboard);
        this.add(new JLabel());
        JPanel home = new JPanel(new GridLayout(2, 3));
        home.setOpaque(false);
        home.add(new JLabel());
        home.add(new JLabel());
        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        homeButton.setBackground(new Color(0, 123, 255));
        homeButton.setForeground(Color.WHITE);
        home.add(homeButton);
        home.add(new JLabel());
        home.add(new JLabel());
        home.add(new JLabel());
        this.add(home);
        this.add(new JLabel());
        currentPlayer = new JLabel("");
        currentPlayer.setHorizontalAlignment(JLabel.CENTER);
        this.add(currentPlayer);
        this.add(new JLabel());
        this.add(new JLabel());
        // question.setHorizontalAlignment(JLabel.CENTER);
        this.add(question);
        this.add(new JLabel());
        this.add(new JLabel());
        JPans = new JPanel(new GridLayout(3, 1));
        JPans.setOpaque(false);
        JPans.add(new JLabel());
        answer = new JTextField();
        int n = this.ans.length();
        gr = new GridLayout(1,n);
        answerPanel = new JPanel(gr);
        answerPanel.setOpaque(false);
        prepareAnswerPane(n);
        JPans.add(answerPanel);
        JPans.add(new JLabel());
        this.add(JPans);
        this.add(new JLabel());
        this.add(new JLabel());
        JPanel validation = new JPanel(new GridLayout(3, 3));
        validation.setOpaque(false);
        valider = new JButton("Valider");
        valider.addActionListener(this);
        valider.setBackground(new Color(40, 167, 69));
        valider.setForeground(Color.WHITE);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    validation.add(valider);
                } else {
                    validation.add(new JLabel());
                }
            }
        }
        this.add(validation);
        this.add(new JLabel());
    }

    public void readFile() throws ParserConfigurationException, SAXException, IOException {
        File file = new File("questions.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("item");
        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                quests.put(eElement.getElementsByTagName("quest").item(0).getTextContent(),
                        eElement.getElementsByTagName("answer").item(0).getTextContent());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == valider) {
            traitement();
        }
        if (e.getSource() == homeButton) {
            window.setVisible(false);
            new MainMenu();
        }
    }

    public void prepareAnswerPane(int n) {
        answerPanel.removeAll();
        gr.setColumns(n);
        answerPanel.setLayout(gr);
        ArrayList<Integer> indexes = new ArrayList<>(cryptedWord.keySet());
        for (int i = 0; i < n; i++) {
            if (this.ans.charAt(i) != ' ') {
                JTextField jt=new JTextField();
                jt.addKeyListener(this);
                for (Integer integer : indexes) {
                    if(i==integer.intValue()){
                         jt.setText(cryptedWord.get(integer)+"");
                         jt.setForeground(Color.GREEN);
                         jt.setEnabled(false);
                        break;}
                }
                jt.setPreferredSize(new Dimension(20, 20));
                jt.setAlignmentY(JTextField.CENTER_ALIGNMENT);
                jt.setAlignmentX(JTextField.CENTER_ALIGNMENT);
                answerPanel.add(jt);
            } else {
                JLabel jl = new JLabel("  ");
                jl.setOpaque(false);
                jl.setPreferredSize(new Dimension(10, 20));
                answerPanel.add(jl);
            }

        }
        
    }

    public void traitement() {
        if (quests.isEmpty()) {
            valider.setEnabled(false);
            question.setText("Aucune question disponible");
            if (scorep1 != scorep2) {
                if (scorep1 > scorep2) {
                    currentPlayer.setText(players[0] + " gagné");
                }
                if (scorep1 < scorep2) {

                    currentPlayer.setText(players[1] + " gagné");
                }
                currentPlayer.setForeground(Color.GREEN);
            } else if (scorep1 == scorep2) {
                currentPlayer.setText("ni personne gagne");
            }
            currentPlayer.setFont(new Font("Serif", Font.PLAIN, 30));
            answer.setVisible(false);
        } else {
            currentPlayer.setText(players[i]);
            i = 1 - i;
            Component[] componentList = answerPanel.getComponents();
            String ch="";
            for(Component c : componentList){
                if(c instanceof JTextField)
                    ch+=((JTextField)c).getText();
            }
            System.out.println(ch);
            if (answer.getText().equals(ch)) {
                if (i == 0) {
                    scorep1++;
                    score.setElementAt(scorep1, i);
                } else {
                    scorep2++;
                    score.setElementAt(scorep2, i);
                }
            }
            ques = keylist.get(generator.nextInt(quests.size()));
            ans = quests.get(ques);
            prepareAnswerPane(ans.length());
            question.setText(ques);
            answer.setText("");
            cryptedWord = getCyptedWord();
            cryptedWord.entrySet().forEach(entry -> {
                System.out.println(entry.getKey() + " " + entry.getValue());
            });
            quests.remove(ques);
            // update keylist to avoid getting questions already deleted
            keylist = new ArrayList<>(quests.keySet());
            s.setOpaque(false);
            if (scorep1 == 10 || scorep2 == 10) {
                valider.setEnabled(false);
                question.setText("Jeu terminer");
                if (scorep1 == 10) {
                    currentPlayer.setText(players[0] + " gagné");
                }
                if (scorep2 == 10)
                    currentPlayer.setText(players[1] + " gagné");
                currentPlayer.setForeground(Color.GREEN);
                currentPlayer.setFont(new Font("Serif", Font.PLAIN, 30));
                answer.setVisible(false);
            }
        }
    }

    public Map<Integer, Character> getCyptedWord() {

        Map<Integer, Character> cryptedWord = new HashMap<Integer, Character>();
        String test = ans;

        if (ans.length() > 12) {
            Random random1 = new Random();
            int index = random1.nextInt(test.length());
            char a = test.charAt(index);
            cryptedWord.put(index, a);
            test = test.replaceFirst(String.valueOf(a), "");

            Random random2 = new Random();
            int index2 = random2.nextInt(test.length());
            char a2 = test.charAt(index2);
            cryptedWord.put(index2, a2);
            test = test.replaceFirst(String.valueOf(a2), "");

            Random random3 = new Random();
            int index3 = random3.nextInt(test.length());
            char a3 = test.charAt(index3);
            cryptedWord.put(index3, a3);
            test = test.replaceFirst(String.valueOf(a3), "");

        }

        else if (ans.length() <= 12 || ans.length() >= 9) {

            Random random = new Random();
            int index = random.nextInt(test.length());
            char a = test.charAt(index);
            cryptedWord.put(index, a);

            Random random2 = new Random();
            int index2 = random2.nextInt(test.length());
            char a2 = test.charAt(index2);
            cryptedWord.put(index2, a2);
            test = test.replaceFirst(String.valueOf(a2), "");

        }

        else {
            Random random = new Random();
            int index = random.nextInt(test.length());
            char a = test.charAt(index);
            cryptedWord.put(index, a);
        }

        return cryptedWord;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()>=65 && e.getKeyCode() <=90){
            int i=0;
            Component[] componentList = answerPanel.getComponents();
            for(Component c : componentList){
                i++;
                if(c==e.getSource())
                    break;
            }
            JTextField jttemp=(JTextField)componentList[i];
            jttemp.requestFocus();
        }
            
    }
}
