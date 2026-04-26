package org.example.MapMatching.RTree;

import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;

public class RPoint {
    private double Lat, Lon;
    public RPoint(double Lat, double Lon) {
        this.Lat = Lat;
        this.Lon = Lon;
    }
    public RPoint(Node node) {
        this.Lat = node.getLat();
        this.Lon = node.getLon();
    }

    public RPoint create(double lat ,double lon){
        return new RPoint(lat,lon);
    }

    public double distance(RPoint point) {
        double lat1 = Math.toRadians(this.Lat);
        double lon1 = Math.toRadians(this.Lon);
        double lat2 = Math.toRadians(point.getLat());
        double lon2 = Math.toRadians(point.getLon());
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));

        return rad * c;
    }

    public double distance(Point point) {
        return distance(new RPoint(point.getLat(), point.getLon()));
    }


    public double getLat() {
        return Lat;
    }

    public double getLon() {
        return Lon;
    }
}
