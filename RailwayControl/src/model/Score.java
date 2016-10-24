package model;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Score {
	private static File file;
	private static int[] scoreList = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static final int LOOSER = -1;
	
	public Score(String filePath) throws ParserConfigurationException, TransformerException {
        file = new File(filePath);
        try {
			loadScore();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			refreshScore();
		}
	}
	
	private static void refreshScore() throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
		Element scores = doc.createElement("Scores");
        doc.appendChild(scores);
        //1
        Element score1 = doc.createElement("Score1");
        score1.appendChild(doc.createTextNode(scoreList[0] + ""));
        scores.appendChild(score1);
        //2
        Element score2 = doc.createElement("Score2");
        score2.appendChild(doc.createTextNode(scoreList[1] + ""));
        scores.appendChild(score2);
        //3
        Element score3 = doc.createElement("Score3");
        score3.appendChild(doc.createTextNode(scoreList[2] + ""));
        scores.appendChild(score3);
        //4
        Element score4 = doc.createElement("Score4");
        score4.appendChild(doc.createTextNode(scoreList[3] + ""));
        scores.appendChild(score4);
        //5
        Element score5 = doc.createElement("Score5");
        score5.appendChild(doc.createTextNode(scoreList[4] + ""));
        scores.appendChild(score5);
        //6
        Element score6 = doc.createElement("Score6");
        score6.appendChild(doc.createTextNode(scoreList[5] + ""));
        scores.appendChild(score6);
        //7
        Element score7 = doc.createElement("Score7");
        score7.appendChild(doc.createTextNode(scoreList[6] + ""));
        scores.appendChild(score7);
        //8
        Element score8 = doc.createElement("Score8");
        score8.appendChild(doc.createTextNode(scoreList[7] + ""));
        scores.appendChild(score8);
        //9
        Element score9 = doc.createElement("Score9");
        score9.appendChild(doc.createTextNode(scoreList[8] + ""));
        scores.appendChild(score9);
        //10
        Element score10 = doc.createElement("Score10");
        score10.appendChild(doc.createTextNode(scoreList[9] + ""));
        scores.appendChild(score10);
        //
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
		transformer.transform(source, result);
	}
	
	private static void loadScore() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		f.setValidating(false);
		DocumentBuilder builder = f.newDocumentBuilder();
		Document doc = builder.parse(file);
        NodeList nodes = doc.getChildNodes();
        Node content = nodes.item(0);
        NodeList scores = content.getChildNodes();
        for(int index = 0; index < scores.getLength(); index++) {
        	Node node = scores.item(index);
            scoreList[index] = Integer.parseInt(node.getTextContent());
        }
	}
	
	public static int add(int score) throws ParserConfigurationException, SAXException, IOException {
        if(score > scoreList[9]) {
        	int index;
        	for(index = 0; index < 10; index++) {
            	int tempScore = scoreList[index];
            	if(score <= tempScore)
            		continue;
            	else
            		break;
            }
        	for(int listIndex = 9; listIndex >= index + 1; listIndex--) {
        		scoreList[listIndex] = scoreList[listIndex - 1];
        	}
        	scoreList[index] = score;
        	try {
				refreshScore();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
        	return index;
        }
        else return LOOSER;
	}
	
	public static void removeStats() {
		for(int index = 0; index < 10; index++) {
			scoreList[index] = 0;
			try {
				refreshScore();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int get(int index) {
		return scoreList[index];
	}
	
	public static int size() {
		return 10;
	}
}
