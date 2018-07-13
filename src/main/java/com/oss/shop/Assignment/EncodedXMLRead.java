package com.oss.shop.Assignment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class EncodedXMLRead {
	private static final String IncorrectXml = "<?xml version=\'1.0\' standalone=\'no\' ?>\n<x>Motörhead</x>";
	private static final String CorrectXml = "<?xml version=\'1.0\' encoding=\'ISO-8859-1\' standalone=\'no\' ?>\n<x>Motörhead</x>";

	public static void main(String[] args)
			throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		DocumentBuilder doBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document inCompleteDoc = doBuilder.parse(new ByteArrayInputStream(IncorrectXml.getBytes(StandardCharsets.UTF_8)));
		inCompleteDoc.getDocumentElement().normalize();
		System.out.println("IncorrectXml : " + convertDocumentToString(inCompleteDoc));		
		Document completeDoc = doBuilder.parse(new ByteArrayInputStream(CorrectXml.getBytes()));
		completeDoc.getDocumentElement().normalize();
		System.out.println("CorrectXml : " + convertDocumentToString(completeDoc));
	}

	private static String convertDocumentToString(Document doc) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			// below code to remove XML declaration
			// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return null;
	}
}
