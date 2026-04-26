package org.example.Graph.Element.ElementUtils;

import org.example.Graph.Element.Point;

import java.util.List;

public class SpatialObjectUtil {

    public static Point center(List<Point> points){
        int n = points.size();
        double lat = 0;
        double lon = 0;
        for(Point point : points){
            lat+=point.GetLat();
            lon+=point.GetLon();
        }
        return new Point(lat/n , lon/n);
    }
}
