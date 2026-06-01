package org.example.ServiceRequest;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Point;

import java.util.Map;

public class MapMatchingResult {
    private Edge edge;
    private Point realPoint;
    private Point mapPoint;
    private Map<String,String> wayTags;

    public Map<String, String> getWayTags() {
        return wayTags;
    }

    public void setWayTags(Map<String, String> wayTags) {
        this.wayTags = wayTags;
    }

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
