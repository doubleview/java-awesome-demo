package com.doubleview.test;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TestDom4j {

	class MyVister extends VisitorSupport {

		private String currentElement;

		@Override
		public void visit(Attribute node) {
			System.out.println(currentElement + "  attribute " + node.getName()
					+ " value is " + node.getText());
		}

		@Override
		public void visit(Element element) {
			if (element.isTextOnly()) {
				System.out.println(element.getName() + " value is "
						+ element.getText());
			}
			currentElement = element.getName();
		}

		@Override
		public void visit(ProcessingInstruction node) {
			System.out.println("process instruction" + node.getTarget()
					+ " content is" + node.getText());
		}
	}

	@SuppressWarnings("rawtypes")
	public void parseAttr(Element element) {
		List attList = element.attributes();
		for (Object e : attList) {
			Attribute attr = (Attribute) e;
			System.out.println(element.getQName().getName() + " element s "
					+ attr.getQName().getName() + "  attribute value "
					+ attr.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	public void parseElement(Element element) {
		parseAttr(element);
		List<Element> el = element.elements();
		for (Element e : el) {
			if (!e.isTextOnly()) {
				parseElement(e);
			} else {
				parseAttr(e);
				System.out.println(e.getQName().getName() + "------->"
						+ e.getText());
			}
		}
	}

	@Test
	public void testModify() throws Exception {
		SAXReader reader = new SAXReader();
		reader.setStripWhitespaceText(true);
		reader.setMergeAdjacentText(true);
		Document document = reader.read(new FileInputStream("book-list.xml"));
		Element element = document.getRootElement();
		Element computerElement = element.addElement("computer");
		computerElement.addAttribute("ISBN", "33333");
		Element nameElement = computerElement.addElement("name");
		nameElement.setText("javascript pattern design");
		Element pricElement = computerElement.addElement("price");
		pricElement.setText("45");

		OutputFormat format = new OutputFormat("", true, "utf-8");
		FileWriter fw = new FileWriter("book-list.xml");
		XMLWriter xmlWriter = new XMLWriter(fw, format);
		xmlWriter.write(document);
		fw.close();
	}

	@Test
	public void testParse() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(ClassLoader
				.getSystemResourceAsStream("book-list.xml"));
		Element element = document.getRootElement();
		System.out.println(element.getName());
		parseElement(element);
	}

	@Test
	public void testVisit() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(ClassLoader
				.getSystemResourceAsStream("book-list.xml"));
		document.accept(new MyVister());
	}

	@Test
	public void testWrite() throws IOException {
		DocumentFactory documentFactory = new DocumentFactory();
		Document document = documentFactory.createDocument();
		document.addProcessingInstruction("hcc", "href=\"www.doubleview.com\"");

		Element element = document.addElement("person");

		Element name = element.addElement("name");
		name.setText("hcc");

		Element age = element.addElement("age");
		age.setText("23");

		OutputFormat outputFormat = new OutputFormat("", true, "utf-8");
		FileWriter fw = new FileWriter("testDom4j.xml");
		XMLWriter writer = new XMLWriter(fw, outputFormat);
		writer.write(document);
		fw.close();
	}

}
