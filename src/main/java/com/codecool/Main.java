package com.codecool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Main {
    public static void main(String[] args) {
        try {
            // domPrinterTest();
        } catch (Exception e) {
            System.out.println(e);
        }
        // collectionTest();
        storeTest();
        // storeLogicTest();
    }

    public static void domPrinterTest() throws Exception {

        DomPrinter dp = new DomPrinter();
        System.out.println(dp.print("src/test2.xml"));

        // Test the printing of a sample document
        // Test printing of a runtime generated DOM object
        DocumentBuilderFactory dFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dFac.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element root = doc.createElement("characters");
        Element nilb = doc.createElement("character");
        doc.appendChild(root);
        root.appendChild(nilb);
        Element nilbName = doc.createElement("name");
        nilbName.appendChild(doc.createTextNode("Nilb"));

        nilb.appendChild(nilbName);
        // System.out.println(dp.print(doc));

        TransformerFactory transFact = TransformerFactory.newInstance();
        Transformer transformer = transFact.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("src/test2.xml"));
        transformer.transform(source, result);

    }

    public static void collectionTest() {
        System.out.println();

        List<String> list = new ArrayList<>();
        list.add("lahaha");
        list.add("hehehő");
        // list.remove(0);
        System.out.println(list.get(0));

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "fasz");
        System.out.println(map.get(7) == null);

    }

    public static void storeTest() {
        PersistentStore ps = new PersistentStore();


        StoreManager storeManager = new StoreManager();
        storeManager.addStorage(ps);

        storeManager.addCDProduct("Cheap Fuckers: Wanker!", 10, 55);
        storeManager.addBookProduct("How to shovel your grandma", 23, 3453453);



    }

    public static void storeLogicTest() {

    }
}
