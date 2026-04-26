package org.example.MapMatching.RTree;

import org.example.Graph.Element.Edge;
import org.example.Graph.Graph.Graph;
import org.example.Graph.Graph.NormalGraph;

public class REdgeFactory {
    private Graph graph;

    REdgeFactory(Graph normalGraph) {
        this.graph = normalGraph;
    }

    public REdge create(Edge edge){
        double lat1 = graph.getNode(edge.getHeadId()).getLat();
        double lng1= graph.getNode(edge.getHeadId()).getLon();
        double lat2 = graph.getNode(edge.getTailId()).getLat();
        double lng2= graph.getNode(edge.getTailId()).getLon();

        return new REdge(edge,new RPoint(lat1,lng1), new RPoint(lat2,lng2));
    }
}
