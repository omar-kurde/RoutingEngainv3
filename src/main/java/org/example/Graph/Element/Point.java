package org.example.Graph.Element;

public class Point {
    private final double lat;
    private final double lon;

    public Point(double Lat, double lon) {
        this.lat = Lat;
        this.lon = lon;
    }

    public double GetLat() {
        return lat;
    }

    public double GetLon() {
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
