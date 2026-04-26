package org.example.MapMatching;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;

import java.util.regex.MatchResult;

public class MapMatchingStrategy implements MapMatching{
    private final NearestNodeFinder nearestNodeFinder;
    private final NearestEdgeFinder nearestEdgeFinder;
    public MapMatchingStrategy(NearestNodeFinder nearestNodeFinder, NearestEdgeFinder nearestEdgeFinder) {
        this.nearestNodeFinder = nearestNodeFinder;
        this.nearestEdgeFinder = nearestEdgeFinder;
    }
    @Override
    public Node nearestNode(Point point) {
        return nearestNodeFinder.nearestNode(point);
    }

    @Override
    public Edge nearestEdge(Point point) {
        return nearestEdgeFinder.nearestEdge(point);
    }
}
