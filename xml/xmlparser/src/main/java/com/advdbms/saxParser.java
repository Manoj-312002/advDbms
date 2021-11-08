package com.advdbms;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;

public class saxParser extends DefaultHandler {
    public void startDocument(){ System.out.println("Document Begins"); }
    public void startElement(String uri , String name , String qname , Attributes atr){ System.out.println("<" + qname + ">" ); } 
    public void characters(char ch[] , int s , int l ){ System.out.println(new String(ch,s,l) ); }
    public void endElement(String uri , String name , String qname ){ System.out.println("</" + qname + ">"); }
    public void endDocument(){System.out.println("Document ends");}

    public static void main(String[] args) throws Exception{
        SAXParser p = SAXParserFactory.newInstance().newSAXParser();
        p.parse(new FileInputStream("/media/ssmj/File/Programming/webdev/advdbms/xml/xmlparser/src/main/trial.xml"), new saxParser() );
    }
}
