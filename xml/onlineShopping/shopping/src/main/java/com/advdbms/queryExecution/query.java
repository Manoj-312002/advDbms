package com.advdbms.queryExecution;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;

// import java.io.*;
import net.sf.saxon.s9api.*;

public class query {
    public static String evaluateXQuery(final String xml, final String query) throws SaxonApiException {
        Processor processor = new Processor(false);
        XQueryCompiler xqueryCompiler = processor.newXQueryCompiler();
        
        XQueryExecutable xqueryExec = xqueryCompiler.compile(query);
        XQueryEvaluator xqueryEval = xqueryExec.load();
        
        SAXSource requestXml = new SAXSource(new InputSource(xml));
        xqueryEval.setSource(requestXml);
        
        xqueryEval.run();
        return "hi";
    }
    public static void main(String[] args) throws Exception{
        // String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><products><product><name>product-1</name><cost>10000</cost><qty>22</qty><likes>101</likes><reviews><review>review-1-1</review><review>review-2-1</review></reviews></product></products>";
        System.out.println( evaluateXQuery( "http://localhost:8000/productsXsl.xml", "/products/product[2]/name")  );        
    }    
}
