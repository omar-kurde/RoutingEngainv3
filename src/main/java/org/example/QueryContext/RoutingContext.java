package org.example.QueryContext;

import org.example.Graph.Element.Point;
import org.example.Graph.Graph.Graph;

import java.util.List;

// in future i will update it to work with interfaces HASxxxx
public class RoutingContext {
    private Point startPoint;
    private Point endPoint ;
    private Long startNodeId , endNodeId ;
    private List<Long> pathIds;
    private List<Point> PathPoints;
    private double cost;
    public RoutingContext(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
}
