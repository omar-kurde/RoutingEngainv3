package org.example.Graph.Element;

public class Point {
    private final double lat;
    private final double lon;

    public Point(double Lat, double lon) {
        this.lat = Lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "Point{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
