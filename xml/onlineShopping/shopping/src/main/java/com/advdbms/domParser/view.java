package com.advdbms.domParser;

import java.io.File;
import javax.xml.parsers.*;

import org.w3c.dom.*;

public class view {
    public static void main(String[] args) throws Exception{
        File f = new File("/media/ssmj/File/Programming/webdev/advdbms/xml/onlineShopping/webpage/products.xml");
        DocumentBuilderFactory fc = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = fc.newDocumentBuilder();
        Document d = db.parse(f);
        
        System.out.println("Root Element Name : " + d.getDocumentElement().getNodeName());
        NodeList sl = d.getElementsByTagName("product");

        for(int i = 0; i < sl.getLength(); i++ ){
            Node n = sl.item(i);
            // System.out.println(n.getNodeName());
            Element e = (Element)n;
            System.out.println( "\tname : " + e.getElementsByTagName("name").item(0).getTextContent()  );
            System.out.println( "\tPrice : " + e.getElementsByTagName("cost").item(0).getTextContent()  );
            System.out.println( "\tQuantity : " + e.getElementsByTagName("qty").item(0).getTextContent()  );
            System.out.println( "\tLikes : " + e.getElementsByTagName("likes").item(0).getTextContent()  );
            System.out.println( "\tReviews" );
            NodeList r = e.getElementsByTagName("review");
            
            for(int j = 0; j < r.getLength(); j++ ){
                System.out.println("\t\t" + r.item(j).getTextContent() );
            }
            System.out.println();
        }        
    }    
}
