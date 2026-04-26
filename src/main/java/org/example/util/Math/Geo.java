package org.example.util.Math;


import org.example.Graph.Element.Point;

public class Geo {

    static final double R = 6378137.0; // نصف قطر الأرض بالمتر


    public static double latToMercator(double lat) {
        return R * Math.log(Math.tan(Math.PI / 4 + Math.toRadians(lat) / 2));
    }
    public static double lonToMercator(double lon) {
        return R * Math.toRadians(lon);
    }
    public static double mercatorToLat( double x) {

        return Math.toDegrees(2 * Math.atan(Math.exp(x / R)) - Math.PI / 2);
    }
    public static double mercatorToLon(double y) {
        return Math.toDegrees(y / R);
    }


    public static Double GetEdgeCost(Point start, Point end) {
        return GetEdgeCost(start.GetLat() , start.GetLon() , end.GetLat() , end.GetLon());
    }
    public static Double GetEdgeCost(Double lat1,Double lon1,Double lat2,Double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        double dLat = (lat2 - lat1);
        double dLon = (lon2 - lon1);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));

        return rad * c;
    }

}
