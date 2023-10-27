package com.akshayram.leetcode;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile {
	public static void main(String argv[]) {
		try {
			File file = new File("C:\\Users\\akshay\\Desktop\\5521\\mismatch5521_100.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			//System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
			StringBuilder sb = new StringBuilder();
			sb.append("CHEQUEDETAIL");
			float total = 0;

			for (int i = 1; i <= 100; i++) {
				sb = new StringBuilder("CHEQUEDETAIL" + i);
				//System.out.println("For " + sb.toString());
				NodeList nodeList = doc.getElementsByTagName(sb.toString());
				for (int itr = 0; itr < nodeList.getLength(); itr++) {
					Node node = nodeList.item(itr);
					//System.out.println("\nNode Name :" + node.getNodeName());
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						//System.out.println("Account: " + eElement.getElementsByTagName("AccNum").item(0).getTextContent());
						//System.out.println("Amount: " + eElement.getElementsByTagName("Amount").item(0).getTextContent());
						float amount = Float.parseFloat(eElement.getElementsByTagName("Amount").item(0).getTextContent());
						// System.out.println(amount);
						total += amount;
					}
				}
			}
			System.out.println("Total Amount=" + total);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
