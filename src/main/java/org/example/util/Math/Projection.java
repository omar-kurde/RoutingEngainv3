package org.example.util.Math;


import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;
import org.example.MapMatching.RTree.RPoint;

import static org.example.util.Math.Geo.*;
import static org.example.util.Math.Vector.*;

public class Projection {

    public static Point closestPointOnLine(Node A, Node B, Point p) {
        return closestPointOnLine(A.getPoint(), B.getPoint(), p);
    }
    public static Point closestPointOnLine(Point A, Point B, Point P) {
        Point a = new Point(latToMercator(A.GetLat()), lonToMercator(A.GetLon()));
        Point b = new Point(latToMercator(B.GetLat()), lonToMercator(B.GetLon()));
        Point p = new Point(latToMercator(P.GetLat()), lonToMercator(P.GetLon()));


        Point AB = subtract(b,a) ;//b.subtract(a);
        Point AP = subtract(p,a);// p.subtract(a);


        double t = dot(AB,AP) / dot(AB,AB);


        // يمكن هنا تقييد t بين 0 و 1 إذا أردت النقطة أن تكون فقط على قطعة مستقيمة وليس خط لا نهائي
        t = Math.max(0, Math.min(1, t));


        Point a2 =  add(a,scale(AB,(t)));

        return new Point(mercatorToLat(a2.GetLat() ) ,mercatorToLon(a2.GetLon()));
    }

    public static Point closestPointOnLine(RPoint A, RPoint B, RPoint P) {
        Point a = new Point(latToMercator(A.getLat()), lonToMercator(A.getLon()));
        Point b = new Point(latToMercator(B.getLat()), lonToMercator(B.getLon()));
        Point p = new Point(latToMercator(P.getLat()), lonToMercator(P.getLon()));


        Point AB = subtract(b,a) ;//b.subtract(a);
        Point AP = subtract(p,a);// p.subtract(a);


        double t = dot(AB,AP) / dot(AB,AB);


        // يمكن هنا تقييد t بين 0 و 1 إذا أردت النقطة أن تكون فقط على قطعة مستقيمة وليس خط لا نهائي
        t = Math.max(0, Math.min(1, t));


        Point a2 =  add(a,scale(AB,(t)));

        return new Point(mercatorToLat(a2.GetLat() ) ,mercatorToLon(a2.GetLon()));
    }

}
