package org.jdom2;

public class BusStop {
    String id;
    double lat;
    double lon;
    String name;
    String oldName;
    String wheelchair;
    boolean valid = false;
    double distance = 0.0;

    public void calculateDistance(double targetLat, double targetLon) {
        double R = 6371000; // metres
        double phi1 = Math.toRadians(this.lat);
        double phi2 = Math.toRadians(targetLat);
        double dphi = Math.toRadians(targetLat - this.lat);
        double dl = Math.toRadians(targetLon - this.lon);

        double a = Math.sin(dphi / 2) * Math.sin(dphi / 2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(dl / 2) * Math.sin(dl / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        this.distance = R * c;
    }

    @Override
    //nice format yey
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Megálló: \n");
        sb.append("  Név: ").append(name);
        if (oldName != null) sb.append(" (").append(oldName).append(")");
        sb.append("\n  Kerekesszék: ").append(wheelchair != null ? wheelchair : "n/a");
        sb.append("\n  Távolság: ").append(String.format("%.2f", distance)).append(" m");
        return sb.toString();
    }
}