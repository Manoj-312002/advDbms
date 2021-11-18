package com.advdbms.saxParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.util.Scanner;


public class modify extends DefaultHandler{
    public String sr , ans = "";
    boolean pr = false ,  qt = false , nm = false;
    public void startElement( String uri , String lname , String qname , Attributes attr ){
        switch( qname ){
            case "name":
                nm = true;
                qt = false;
                break;
            case "qty":
                qt = true;
                nm = false;
                break;
            default:
                nm = false;
                qt = false;
                break;
        }

        ans = ans + "<" + qname + ">" ;
    }

    public void characters( char ch[] , int s , int l ){
        String ct = new String(ch,s,l);
        if(nm){
            if( ct.equals(sr) ) pr = true;
            else pr = false;
        }
        if( qt && pr ){
            ct = (Integer.parseInt(ct) - 1) + "";
        }

        ans += ct;
    }

    public void endElement(String uri , String lname , String qname ){
        ans = ans + "</" + qname + ">" ;
    }
    public void print() throws Exception{
        ans = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + ans;
        FileOutputStream fos = new FileOutputStream("/media/ssmj/File/Programming/webdev/advdbms/xml/onlineShopping/webpageXML/productJava.xml");
        fos.write(ans.getBytes());
        fos.close();
    }
    public static void main(String[] args) throws Exception{
        SAXParser p = SAXParserFactory.newInstance().newSAXParser();
        modify v = new modify();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product name to decrement quantity : ");
        String s = sc.nextLine();
        v.sr = s;
        p.parse(new FileInputStream("/media/ssmj/File/Programming/webdev/advdbms/xml/onlineShopping/webpageXML/productJava.xml"), v );
        v.print();
        sc.close();
    } 
}
