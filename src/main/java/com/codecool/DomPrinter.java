package com.codecool;


import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;


public class DomPrinter {
    private String printStr;
    private static String indentIncrease = "   ";

    public String print(String filename) throws Exception {
        System.out.println("XML Dom Tester");
        DocumentBuilderFactory buildFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = buildFac.newDocumentBuilder();

        Document document = builder.parse(new FileInputStream(filename));
        return this.print(document);

    }

    public String print(Document document) throws Exception {
        printStr = "";
        Element root = document.getDocumentElement();
        printParams(root, "");
        goToElement(root, indentIncrease);

        return printStr;
    }

    private void goToElement(Element element, String indent) throws Exception {
        NodeList nodes = element.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element childElement = (Element) node;
                printParams(childElement, indent);
                // childElement.getChildNodes().getLength()
                if (childElement.getChildNodes().getLength() > 1) {

                    String indentStr = "";
                    goToElement(childElement, indent + indentIncrease);
                }


            }
        }
    }

    private void printParams(Element element, String indent) {
        printStr += indent + element.getTagName();
        if (element.getChildNodes().getLength() == 1) {
            printStr += ": " + element.getTextContent();
        }
        printStr += "\n";
    }

}
