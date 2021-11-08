package com.advdbms;

import java.io.File;
import javax.xml.parsers.*;

import org.w3c.dom.*;


public class parser1 {
    public static void main(String[] args) throws Exception {
        File f = new File("/media/ssmj/File/Programming/webdev/advdbms/xml/xmlparser/src/main/trial.xml");
        DocumentBuilderFactory fc = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = fc.newDocumentBuilder();
        Document d = db.parse(f);
        
        System.out.println(d.getDocumentElement().getNodeName());
        NodeList sl = d.getElementsByTagName("student");

        for(int i = 0; i < sl.getLength(); i++ ){            
            Node n = sl.item(i);
            System.out.println(n.getNodeName());
            Element e = (Element)n;
            System.out.println( "name : " + e.getElementsByTagName("name").item(0).getTextContent()  );
        }
    }    
}
