package com.advdbms.domParser;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import java.util.Scanner;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class create {
    public static void main(String[] args) throws Exception{
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder b = f.newDocumentBuilder();
        Document d = b.newDocument();
        Scanner sc = new Scanner(System.in);
        Element root = d.createElement("products");

        while(true){
            Element product = d.createElement("product");
            Element name = d.createElement("name");
            Element cost = d.createElement("cost");
            Element qty = d.createElement("qty");
            Element likes = d.createElement("likes");
            Element reviews = d.createElement("reviews");

            System.out.println("Enter name, category , cost, qty , likes");
            String na = sc.nextLine();
            if( na.equals("-1") ) break;
            Text t1 = d.createTextNode( na ); name.appendChild(t1);
            product.setAttribute("category", sc.nextLine() );
            Text t2 = d.createTextNode( sc.nextLine() ); cost.appendChild(t2);
            Text t3 = d.createTextNode( sc.nextLine() ); qty.appendChild(t3);
            Text t4 = d.createTextNode( sc.nextLine() ); likes.appendChild(t4);

            System.out.println("Enter reviews");
            while(true){
                String rv = sc.nextLine();
                if(rv.equals("-1")) break;
                Element review = d.createElement("review");
                Text t5 =  d.createTextNode(rv); review.appendChild(t5);
                reviews.appendChild( review  );
            }
            
            product.appendChild(name);
            product.appendChild(cost);
            product.appendChild(qty);
            product.appendChild(likes);
            product.appendChild(reviews);
            root.appendChild(product);
        }
        
        d.appendChild(root);        
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(d) , new StreamResult( new FileOutputStream("/media/ssmj/File/Programming/webdev/advdbms/xml/onlineShopping/webpageXML/productJava.xml") ) );
        sc.close();
    }    
}
