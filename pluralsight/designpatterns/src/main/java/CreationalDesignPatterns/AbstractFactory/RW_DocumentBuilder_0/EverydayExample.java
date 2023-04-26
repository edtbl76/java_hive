package CreationalDesignPatterns.AbstractFactory.RW_DocumentBuilder_0;


import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

public class EverydayExample {

    public static void main(String[] args) throws Exception {
        String xml = "<document><body><stock>AAPL</stock></body></document>";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xml.getBytes());

        DocumentBuilderFactory abstractFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder factory = abstractFactory.newDocumentBuilder();
        Document document = factory.parse(byteArrayInputStream);

        document.getDocumentElement().normalize();

        System.out.println("Root Element: " + document.getDocumentElement().getNodeName());
        System.out.println(abstractFactory.getClass());
        System.out.println(factory.getClass());



    }
}
