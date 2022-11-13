import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Game extends JPanel {
    private String[] players;
    private Map<String, String> quests;

    public Game(String[] players) {
        quests = new HashMap<>();
        this.players = players;
        this.setOpaque(false);
        this.setSize(new Dimension(800, 600));
        this.add(new JLabel("hello"));
        this.setVisible(true);
        try {
            readFile();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void readFile() throws ParserConfigurationException, SAXException, IOException {
        File file = new File("questions.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        NodeList nodeList = doc.getElementsByTagName("item");
        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                quests.put(eElement.getElementsByTagName("quest").item(0).getTextContent(), eElement.getElementsByTagName("answer").item(0).getTextContent());
            }
        }
        System.out.println(quests);
    }
}
