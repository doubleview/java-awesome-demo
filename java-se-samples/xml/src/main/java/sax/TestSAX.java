package sax;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class TestSAX {


	@Test
	public void testValidate() throws SAXException, IOException, ParserConfigurationException{

		XMLReader xmlReader = XMLReaderFactory.createXMLReader();

		xmlReader.setFeature("http://xml.org/sax/features/validation", true);
		xmlReader.setContentHandler(new MyErrorHandler());
		xmlReader.parse("error.xml");

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		saxParserFactory.setValidating(true);
		SAXParser saxParser = saxParserFactory.newSAXParser();
		saxParser.parse("error.xml", new MyErrorHandler());
	}

	class MyErrorHandler extends DefaultHandler{

		public void error(SAXParseException exception) throws SAXException{
			System.out.println("error");
			System.out.println(exception.getSystemId() + exception.getLineNumber() + exception.getColumnNumber());
		}
	}


	@Test
	public void testSaxParse() throws SAXException, IOException, ParserConfigurationException{

		InputStream in = ClassLoader.getSystemResourceAsStream("book-list.xml");

/*		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new MyDefaultHandler());
		reader.setDTDHandler(new MyDefaultHandler());

		reader.parse(in);*/

		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser saxParser = saxParserFactory.newSAXParser();
		saxParser.parse(in, new MyDefaultHandler());
	}


	class MyDefaultHandler extends DefaultHandler {

		private String currentTag;

		@Override
		public void characters(char[] ch , int start , int length){
			String content = new String(ch , start , length);
			if(content.trim().length() > 0){
				System.out.println("<" + currentTag + "> element value :   " + content.trim());
			}
		}

		@Override
		public void endDocument(){
			System.out.println("parse document end");
		}

		@Override
		public void endElement(String uri , String localName , String qName){
			System.out.println("end parse element : " + qName);
		}

		@Override
		public void endPrefixMapping(String prefix){
			System.out.println("<" + currentTag + "> element namespace prefix is " + prefix);
		}

		@Override
		public void ignorableWhitespace(char[] ch , int start , int length){

		}

		@Override
		public void processingInstruction(String target , String data){
			System.out.println("current process order is " + target);
			System.out.println("process data is " + data);
		}

		@Override
		public void skippedEntity(String name){
			System.out.println("SAX parse skip entity name is " + name);
		}

		@Override
		public void startDocument(){
			System.out.println("documment parse start");
		}

		@Override
		public void startElement(String uri , String localName , String qName , Attributes atts){
			System.out.println("start parse element :" + qName);
			currentTag = qName;

			int len = atts.getLength();
			if(len > 0){
				System.out.println("<" + currentTag + "> element attr is");
				for(int i = 0; i< len;i++){
					System.out.println(atts.getQName(i) + "------>" + atts.getValue(i));
				}
			}
		}

		@Override
		public void startPrefixMapping(String prefix , String uri){
			System.out.println("<" + currentTag + ">element namespace prefix is" + prefix);
			System.out.println("<" + currentTag + ">element namespace uri is" + uri);
		}

		@Override
		public void notationDecl(String name , String publicId , String systemId){
			System.out.println("current name " + name);
			System.out.println("current notation public uri" + publicId);
			System.out.println("current system uri" + systemId);
		}

		@Override
		public void unparsedEntityDecl(String name , String publicId , String systemId , String notationName){
			System.out.println("current unresolved name " + name);
			System.out.println("current unresolved public uri " + publicId);
			System.out.println("current unresolved system uri " + systemId);
			System.out.println("current unresolved natation name" + notationName);
		}
	}
}
