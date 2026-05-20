package org.example.Services;

import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;
import org.example.Graph.ElementFactory.NodeFactory;
import org.example.Graph.Graph.Graph;
import org.example.Graph.Graph.TempGraph;
import org.example.MapMatching.MapMatching;
import org.example.Routing.DijkstraRouting.DijkstraRouting;
import org.example.Routing.Routing;
import org.example.ServiceRequest.MapMatchingResult;
import org.example.ServiceResponse.RoutingPath;

public class RoutingService {
    private Routing routing;
    private MapMatchingService mapMatchingService;
    private Graph graph;
    public RoutingService(MapMatchingService mapMatchingService ,Graph graph) {
        this.routing = new DijkstraRouting();
        this.mapMatchingService = mapMatchingService;
        this.graph = graph;
    }

    public RoutingPath shortestRoute(Point start, Point end) {
        MapMatchingResult startRes = mapMatchingService.MatchToEdge(start);
        MapMatchingResult endRes = mapMatchingService.MatchToEdge(end);
        Graph graph = new TempGraph(this.graph);
        Node startNode = NodeFactory.createVirtualNode(startRes.getEdge() , startRes.getMapPoint() , graph);
        Node endNode = NodeFactory.createVirtualNode(endRes.getEdge() , endRes.getMapPoint() , graph);
        return routing.shortestPath(startNode.getId() , endNode.getId() , graph);
    }



}
