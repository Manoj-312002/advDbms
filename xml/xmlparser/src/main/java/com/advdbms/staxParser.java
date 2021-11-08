package com.advdbms;

import java.io.FileReader;

import javax.xml.stream.*;
import javax.xml.stream.events.*;

// written in same class 
public class staxParser {
    public static void main(String[] args) throws Exception{
        XMLInputFactory f = XMLInputFactory.newInstance();
        XMLEventReader r = f.createXMLEventReader(new FileReader("/media/ssmj/File/Programming/webdev/advdbms/xml/xmlparser/src/main/trial.xml"));

        while(r.hasNext()){
            XMLEvent e = r.nextEvent();
            switch( e.getEventType() ){
                case XMLStreamConstants.START_DOCUMENT:
                    StartElement se = e.asStartElement();
                    String qname = se.getName().getLocalPart();
                    if(qname.equals("student")) System.out.println("student");
                    else if(qname.equals("name")) System.out.println("name");
                    break;
                case XMLStreamConstants.CHARACTERS:
                    // Characters ch = e.asCharacters();
                    // System.out.println(ch);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    // EndElement ee = e.asEndElement();    
                    // System.out.println(ee);
                    break;
            }
        }
    }
}
