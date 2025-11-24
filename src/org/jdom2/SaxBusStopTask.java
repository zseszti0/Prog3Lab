package org.jdom2;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SaxBusStopTask extends DefaultHandler {
    private List<BusStop> busStops = new ArrayList<>();
    private BusStop currentStop = null;
    private double refLat, refLon;

    public SaxBusStopTask(double lat, double lon) {
        this.refLat = lat;
        this.refLon = lon;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("node")) {

            currentStop = new BusStop();
            currentStop.id = attributes.getValue("id");
            try {
                currentStop.lat = Double.parseDouble(attributes.getValue("lat"));
                currentStop.lon = Double.parseDouble(attributes.getValue("lon"));

                currentStop.calculateDistance(refLat, refLon);
            } catch (NumberFormatException e) { /* Ignore invalid coords */ }

        } else if (qName.equals("tag") && currentStop != null) {
            // Process tag attributes
            String k = attributes.getValue("k");
            String v = attributes.getValue("v");

            if ("highway".equals(k) && "bus_stop".equals(v)) {
                currentStop.valid = true;
            } else if ("name".equals(k)) {
                currentStop.name = v;
            } else if ("old_name".equals(k)) {
                currentStop.oldName = v;
            } else if ("wheelchair".equals(k)) {
                currentStop.wheelchair = v;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equals("node")) {
            if (currentStop != null && currentStop.valid) {
                busStops.add(currentStop);
            }
            currentStop = null;
        }
    }

    @Override
    public void endDocument() {

        busStops.sort(Comparator.comparingDouble(b -> b.distance));
        for (BusStop b : busStops) {
            System.out.println(b);
            System.out.println("-----------------");
        }
    }

    public static void main(String[] args) {
        String filename = "bme.xml";
        double lat = 47.4786346;
        double lon = 19.0555773;

        // Simple argument parsing
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i") && i + 1 < args.length) filename = args[i+1];
            if (args[i].equals("-lat") && i + 1 < args.length) lat = Double.parseDouble(args[i+1]);
            if (args[i].equals("-lon") && i + 1 < args.length) lon = Double.parseDouble(args[i+1]);
        }

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser p = factory.newSAXParser();
            p.parse(new File(filename), new SaxBusStopTask(lat, lon));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}