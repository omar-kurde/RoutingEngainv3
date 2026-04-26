package org.example.MapMatching.BruteForce;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;
import org.example.Graph.Graph.Graph;
import org.example.MapMatching.NearestEdgeFinder;
import org.example.MapMatching.NearestNodeFinder;
import org.example.util.Math.Geo;
import org.example.util.Math.Projection;

import static org.example.util.Math.Geo.GetEdgeCost;

public class BruteForceMatcher implements NearestEdgeFinder , NearestNodeFinder {

    private Graph graph;
    public BruteForceMatcher(Graph graph) {
        this.graph = graph;
    }
    @Override
    public Node nearestNode(Point point ) {
        double minDistance = Double.MAX_VALUE;
        Node nearestNode = null;
        for (Node node : graph.NODES_LIST()){
            if (!node.isOnWay()) continue;
            Point nodePoint = node.getPoint();
            Double cost = GetEdgeCost(point , nodePoint);
            if (cost < minDistance){
                minDistance = GetEdgeCost(point , nodePoint);
                nearestNode = node;
            }
        }
        return nearestNode;
    }

    @Override
    public Edge nearestEdge(Point point) {
        Double MinDis = 1e9;
        Edge nearestEdge = null;

        for (Edge edge : graph.EDGES_LIST()){
            if (!edge.IsHighWay())continue;
            Point curPoint = Projection.closestPointOnLine(
                    graph.getNode(edge.getHeadId()),
                    graph.getNode(edge.getTailId()) ,
                    point
            );
            Double curCost = GetEdgeCost(point , curPoint);
            if (curCost < MinDis) {
                MinDis = curCost;
                nearestEdge = edge;
            }
        }
        return nearestEdge;
    }
}
