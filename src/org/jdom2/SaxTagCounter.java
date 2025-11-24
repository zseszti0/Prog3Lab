package org.jdom2;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SaxTagCounter extends DefaultHandler {
    private Map<String, Integer> tags = new HashMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        // Count every tag occurrence
        tags.put(qName, tags.getOrDefault(qName, 0) + 1);
    }

    @Override
    public void endDocument() {
        // Print results one by one
        for (Map.Entry<String, Integer> entry : tags.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("-i")) {
            System.out.println("Usage: java org.jdom2.SaxTagCounter -i <xmlfile>");
            return;
        }
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser p = factory.newSAXParser();
            p.parse(new File(args[1]), new SaxTagCounter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}