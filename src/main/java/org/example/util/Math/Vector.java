package org.example.util.Math;


import org.example.Graph.Element.Point;

public class Vector {

    public static Point subtract(Point p1 , Point p2) {
        return new Point(p1.getLat() - p2.getLat(), p1.getLon() - p2.getLon());
    }
    public static double dot(Point p1 , Point p2) {
        return p1.getLat() * p2.getLat() + p1.getLon() * p2.getLon();
    }
    public static Point scale(Point p1,double t) {
        return new Point(p1.getLat() * t, p1.getLon() * t);
    }
    public static Point add(Point p1,Point p2) {
        return new Point(p1.getLat() + p2.getLat(), p1.getLon() + p2.getLon());
    }

}
