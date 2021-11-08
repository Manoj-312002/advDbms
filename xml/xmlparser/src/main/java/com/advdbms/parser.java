package com.advdbms;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class parser {
    public static void main(String[] args) throws Exception{
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        DocumentBuilder b = f.newDocumentBuilder();
        Document d = b.newDocument();

        Element root = d.createElement("students");
        Element student = d.createElement("student");
        Element name = d.createElement("name");

        Text t1 = d.createTextNode("Manoj");

        name.appendChild(t1);
        student.appendChild(name);
        root.appendChild(student);
        d.appendChild(root);

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(d) , new StreamResult( new FileOutputStream("/media/ssmj/File/Programming/webdev/advdbms/xml/xmlparser/src/main/trial.xml") ) );
    }
}
