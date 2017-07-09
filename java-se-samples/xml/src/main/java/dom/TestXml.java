package dom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

public class TestXml {

	@Test
	public  void testDomXml() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("book-list.xml");
		Document document = documentBuilder.parse(inputStream);
		Element booElement = document.getDocumentElement();
		System.out.println("root element childnodes length is " + booElement.getChildNodes().getLength());
		NodeList bookList= booElement.getElementsByTagName("computer");
		System.out.println(bookList.getLength());
		for(int i = 0;i < bookList.getLength();i++){
			Node comBook = bookList.item(i);
			Node isbnAttr = comBook.getAttributes().getNamedItem("ISBN");
			if(isbnAttr != null){
				System.out.println(isbnAttr.getTextContent());
			}

			NodeList attrList = comBook.getChildNodes();
			for(int j = 0;j<attrList.getLength();j++){
				if(attrList.item(j).getNodeType() == Node.ELEMENT_NODE){
					System.out.println(attrList.item(j).getTextContent());
				}
			}
		}
	}

	@Test
	public void testDomXmlOut() throws ParserConfigurationException, ClassCastException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		document.setXmlVersion("1.0");
		ProcessingInstruction processingInstruction = document.createProcessingInstruction("hcc", "href='www.huchengchao.com'");
		document.appendChild(processingInstruction);
		Comment comment = document.createComment("this is a comment");
		document.appendChild(comment);

		Element root = document.createElement("student");
		root.setAttribute("no", "129094328");

		Element item = document.createElement("name");
		item.appendChild(document.createTextNode("huchengchao"));
		root.appendChild(item);

		Element ageElement = document.createElement("age");
		ageElement.appendChild(document.createTextNode("23"));
		root.appendChild(ageElement);

		document.appendChild(root);

		DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
		DOMImplementationLS dLs = (DOMImplementationLS)registry.getDOMImplementation("LS");

		LSSerializer serializer = dLs.createLSSerializer();

		serializer.getDomConfig().setParameter("format-pretty-print", true);

		LSOutput out =  dLs.createLSOutput();
		out.setEncoding("utf-8");

		out.setByteStream(new FileOutputStream("student.xml"));
		serializer.write(document, out);
	}


	@Test
	public void testXmlModify() throws ParserConfigurationException, SAXException, IOException, ClassCastException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(new File("student.xml"));

		Element rootElement = document.getDocumentElement();

		rootElement.getAttributeNode("no").setValue("12345678");

		DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
		DOMImplementationLS dLs = (DOMImplementationLS)registry.getDOMImplementation("LS");

		LSSerializer serializer = dLs.createLSSerializer();

		serializer.getDomConfig().setParameter("format-pretty-print", true);

		LSOutput out =  dLs.createLSOutput();
		out.setEncoding("utf-8");

		out.setByteStream(new FileOutputStream("student.xml"));
		serializer.write(document, out);
	}
}
