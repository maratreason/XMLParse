package com.XML;

import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class ParseXML {

	public static void main(String[] args) {

		Document xmlDoc = getDocument("./src/com/XML/tvshows5.xml");
		System.out.println("Root: " + xmlDoc.getDocumentElement().getNodeName());
		
		NodeList listOfShows = xmlDoc.getElementsByTagName("show");
		System.out.println("Number of shows " + 
				listOfShows.getLength());
		
		String elementName = "network";
		String attrName = "country";
		
		getElementAndAttrib(listOfShows, elementName, attrName);
		
	}

	private static Document getDocument(String docString) {

		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			factory.setValidating(true);
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			return builder.parse(new InputSource(docString));
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	private static void getElementAndAttrib(NodeList listOfShows, String elementName,
			String attrName) {
		
		try {
			
			for(int i = 0; i < listOfShows.getLength(); i++){
				
				Node showNode = listOfShows.item(i);
				
				Element showElement = (Element)showNode;
				
				NodeList networkList = showElement.getElementsByTagName(elementName);
				
				Element networkElement = (Element)networkList.item(0);
				
				NodeList elementList = networkElement.getChildNodes();
				
				if(networkElement.hasAttribute(attrName)) {
					
					System.out.println(elementName + " : " + 
							((Node)elementList.item(0)).getNodeValue().trim() + 
							" has attribute " + networkElement.getAttribute(attrName));
					
				} else {
					
					System.out.println(elementName + " : " + 
							((Node)elementList.item(0)).getNodeValue().trim());
					
				} 
				
			}
			
		} 
		
		catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
		
	}
	

}
