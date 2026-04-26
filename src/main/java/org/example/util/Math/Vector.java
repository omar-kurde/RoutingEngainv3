package org.example.util.Math;


import org.example.Graph.Element.Point;

public class Vector {

    public static Point subtract(Point p1 , Point p2) {
        return new Point(p1.GetLat() - p2.GetLat(), p1.GetLon() - p2.GetLon());
    }
    public static double dot(Point p1 , Point p2) {
        return p1.GetLat() * p2.GetLat() + p1.GetLon() * p2.GetLon();
    }
    public static Point scale(Point p1,double t) {
        return new Point(p1.GetLat() * t, p1.GetLon() * t);
    }
    public static Point add(Point p1,Point p2) {
        return new Point(p1.GetLat() + p2.GetLat(), p1.GetLon() + p2.GetLon());
    }

}
