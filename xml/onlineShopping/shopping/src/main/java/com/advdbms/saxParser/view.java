package com.advdbms.saxParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;


public class view extends DefaultHandler{
    public void startElement( String uri , String lname , String qname , Attributes attr ){
        switch( qname ){
            case "products" : 
                System.out.println("product details");
                break;
            case "product" : 
                System.out.println("product " + attr.getValue("category"));
                break;
            case "reviews":
                System.out.println("Reviews : ");
                break;
            case "review":
                System.out.print("\t");
                break;
            default:
                System.out.print( qname + ": ");
        }

    }

    public void characters( char ch[] , int s , int l ){
        System.out.println( new String(ch,s,l) );
    }

    public void endElement(String uri , String lname , String qname ){
        if( qname.equals("product")) System.out.println( "/product");
    }
    public static void main(String[] args) throws Exception{
        SAXParser p = SAXParserFactory.newInstance().newSAXParser();
        p.parse(new FileInputStream("/media/ssmj/File/Programming/webdev/advdbms/xml/onlineShopping/webpage/products.xml"), new view() );
    }    
}
