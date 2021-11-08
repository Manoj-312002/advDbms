package com.advdbms.domParser;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.Scanner;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class modify {
    public static void main(String[] args) throws Exception{
        File f = new File("/media/ssmj/File/Programming/webdev/advdbms/xml/onlineShopping/webpage/productJava.xml");
        DocumentBuilderFactory fc = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = fc.newDocumentBuilder();
        Document d = db.parse(f);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter name whose likes to increase");
        String sr = sc.nextLine();

        NodeList sl = d.getElementsByTagName("product");

        for(int i = 0; i < sl.getLength(); i++ ){
            Node n = sl.item(i);
            Element e = (Element) n;

            if( e.getElementsByTagName("name").item(0).getTextContent().equals(sr) ){
                int lks = Integer.parseInt( e.getElementsByTagName("likes").item(0).getTextContent() );
                System.out.println("likes " + lks + ":" + (lks+1));
                e.getElementsByTagName("likes").item(0).setTextContent( "" +(lks + 1 ) );
            }

        }
        
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(d) , new StreamResult( new FileOutputStream("/media/ssmj/File/Programming/webdev/advdbms/xml/onlineShopping/webpage/productJava.xml") ) );
        sc.close();
    }    
}
