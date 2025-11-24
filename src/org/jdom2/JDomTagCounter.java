package org.jdom2;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JDomTagCounter {
    static Map<String, Integer> tags = new HashMap<>();

    public static void main(String[] args) {
        if (args.length < 2 || !args[0].equals("-i")) {
            System.out.println("Usage: java org.jdom2.JDomTagCounter -i <xmlfile>");
            return;
        }

        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new File(args[1]));


            countTags(doc.getRootElement());

            for (Map.Entry<String, Integer> entry : tags.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void countTags(Element element) {
        String name = element.getName();
        tags.put(name, tags.getOrDefault(name, 0) + 1);

        for (Element child : element.getChildren()) {
            countTags(child);
        }
    }
}