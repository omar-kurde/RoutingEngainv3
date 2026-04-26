package org.example.ServiceRequest;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Point;

public class MapMatchingResult {
    private Edge edge;
    private Point realPoint;
    private Point mapPoint;

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    public Point getRealPoint() {
        return realPoint;
    }

    public void setRealPoint(Point realPoint) {
        this.realPoint = realPoint;
    }

    public Point getMapPoint() {
        return mapPoint;
    }

    public void setMapPoint(Point mapPoint) {
        this.mapPoint = mapPoint;
    }
}
