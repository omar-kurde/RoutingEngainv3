package org.example.Services;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;
import org.example.Graph.Graph.Graph;
import org.example.MapMatching.MapMatching;
import org.example.ServiceRequest.MapMatchingResult;
import org.example.util.Math.Projection;

public class MapMatchingService {
    private final MapMatching strategy;
    private final Graph graph;
    public MapMatchingService(Graph graph , MapMatching strategy) {
        this.graph = graph;
        this.strategy = strategy;
    }

    public Node MatchToNode(Point point) {
        return strategy.nearestNode(point);
    }
    public MapMatchingResult MatchToEdge(Point point) {
        Edge edge =  strategy.nearestEdge(point);
        MapMatchingResult result =  new MapMatchingResult();
        result.setEdge(edge);
        result.setRealPoint(point);
        Point matchedPoint = Projection.closestPointOnLine(graph.getNode(edge.getHeadId()) , graph.getNode(edge.getTailId()) , point);
        result.setMapPoint(matchedPoint);
        result.setWayTags(graph.getWay(edge.getWayId()).getTags());
        return result;
    }

}
