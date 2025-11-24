package org.jdom2;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

public class JDomBusProcessing {

    public static void main(String[] args) {
        String inputFile = "bme.xml";
        String outputFile = "bme_bus.xml";
        double refLat = 47.4786346;
        double refLon = 19.0555773;

        // Parse Args
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i")) inputFile = args[i+1];
            if (args[i].equals("-o")) outputFile = args[i+1];
            if (args[i].equals("-lat")) refLat = Double.parseDouble(args[i+1]);
            if (args[i].equals("-lon")) refLon = Double.parseDouble(args[i+1]);
        }

        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new File(inputFile));
            Element root = doc.getRootElement();


            List<Element> children = root.getChildren();
            Iterator<Element> iterator = children.iterator();

            while (iterator.hasNext()) {
                Element node = iterator.next();
                boolean isBusStop = false;


                if (node.getName().equals("node")) {
                    for (Element tag : node.getChildren("tag")) {
                        if ("bus_stop".equals(tag.getAttributeValue("v"))) {
                            isBusStop = true;
                            break;
                        }
                    }
                }

                if (!isBusStop) {

                    iterator.remove();
                } else {

                    try {
                        double lat = Double.parseDouble(node.getAttributeValue("lat"));
                        double lon = Double.parseDouble(node.getAttributeValue("lon"));


                        BusStop bs = new BusStop();
                        bs.lat = lat; bs.lon = lon;
                        bs.calculateDistance(refLat, refLon);


                        Element distTag = new Element("tag");
                        // Set attributes
                        distTag.setAttribute("k", "distance");
                        distTag.setAttribute("v", String.valueOf(bs.distance));


                        node.addContent(distTag);

                    } catch (Exception e) { /* Skip calc errors */ }
                }
            }

            // Output to file
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileOutputStream(outputFile));
            System.out.println("Processing complete. Saved to " + outputFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}