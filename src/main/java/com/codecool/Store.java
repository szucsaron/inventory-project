package com.codecool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.IIOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Store implements StorageCapable{
    /*
    The Store class is abstract. The store method accepts a Product instance as its parameter,
    which product is created via the createProduct method. createProduct is implemented in the
    Store class and the concrete product creation (BookProduct or CDProduct) depends on the
    type parameter. It can be "CD" or "Book".

    The store method calls the saveToXml method and the storeProduct method. The store method
    implements a pattern called the Strategy Pattern. It means the execution strategy (calling
    saveToXml and storeProduct ) is fixed even if you inherit from this class.

    The saveToXml method is implemented in the Store abstract class. Calling it saves the incoming product to XML.
     */

    private String filename = "src/test2.xml";
    protected ArrayList<Product> storedProducts = new ArrayList<>();

    @Override
    public List<Product> getAllProducts() {
        return storedProducts;
    }

    @Override
    public void storeBookProduct(String name, int price, int pages) {
        Product product = createProduct("book", name, price, pages);
        store(product);
    }

    @Override
    public void storeCDProduct(String name, int price, int tracks) {
        Product product = createProduct("cd", name, price, tracks);
        store(product);
    }

    private void saveToXml(Product product) throws ParserConfigurationException,
            TransformerException, FileNotFoundException, IOException, SAXException {

        List<Product> products = loadXml(filename);
        products.add(product);
        this.saveToXml(products);
        System.out.println("XML over");
    }

    private void saveToXml(List<Product> products) throws ParserConfigurationException,
            TransformerException, FileNotFoundException, IOException, SAXException {

        DocumentBuilderFactory buildFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        builder = buildFac.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element docProducts = doc.createElement("Products");
        doc.appendChild(docProducts);

        for (Product product : products) {
            Element newProduct = doc.createElement("Product");
            newProduct.setAttribute("name", product.getName());
            newProduct.setAttribute("price", Integer.toString(product.getPrice()));
            newProduct.setAttribute("size", Integer.toString(product.getSize()));
            newProduct.setAttribute("type", product.getType());
            docProducts.appendChild(newProduct);

        }

        TransformerFactory transFact = TransformerFactory.newInstance();
        Transformer transformer = transFact.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
        System.out.println("XML save over");

    }

    private List<Product> loadXml(String filename) throws ParserConfigurationException,
            TransformerException, FileNotFoundException, IOException, SAXException {
        DocumentBuilderFactory buildFac = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;

        builder = buildFac.newDocumentBuilder();

        Document doc = builder.parse(new FileInputStream(filename));
        NodeList docProducts = doc.getDocumentElement().getChildNodes();

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < docProducts.getLength(); i++) {
            Node node = docProducts.item(i);
            if (node instanceof Element) {
                // ((Element) node).getTagName();
                Element product = (Element) node;
                System.out.println(product.getTagName());
                String name = product.getAttribute("name");
                int price = Integer.parseInt(product.getAttribute("price"));
                int size = Integer.parseInt(product.getAttribute("size"));
                String type = product.getAttribute("type");
                products.add(createProduct(type, name, price, size));
            }
        }

        return products;

    }

    private void printXmlErrorMessage(Exception e) {
        System.out.println("Xml save error:");
        System.out.println(e);
    }

    protected void storeProduct(Product product) {

    }

    protected Product createProduct(String type, String name, int price, int size) {
        Product product = new BookProduct("", 0, 0);
        switch (type) {
            case "cd":
                product = new CDProduct(name, price, size);
                break;
            case "book":
                product = new BookProduct(name, price, size);
                break;
        }
        return product;
    }

    public List<Product> loadProducts() {
        return storedProducts;
    }

    public void store(Product product) {
        try {
            saveToXml(product);
        } catch (ParserConfigurationException e) {
            printXmlErrorMessage(e);
        } catch (TransformerException e) {
            printXmlErrorMessage(e);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            printXmlErrorMessage(e);
        } catch (SAXException e) {
            printXmlErrorMessage(e);
        }
        storeProduct(product);
    }
}
