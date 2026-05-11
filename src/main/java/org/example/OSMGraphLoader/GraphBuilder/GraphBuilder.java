package org.example.OSMGraphLoader.GraphBuilder;

import org.example.CONSTANT;
import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Way;
import org.example.Graph.Graph.Graph;
import org.example.OSMGraphLoader.EntityProcessor.EdgeProcessor;

public class GraphBuilder {
    private final Graph graph;
    private EdgeProcessor edgeProcessor;
    public GraphBuilder(Graph graph) {
        this.graph = graph;
        edgeProcessor = new EdgeProcessor();
    }




    public void buildGraph() {

        for (Way way : graph.WAYS_LIST()){
            if (!way.isHighWay())continue; // important
            String dir = way.getTag(CONSTANT.ONE_WAY);
            if (dir == null || dir.isEmpty() || dir.equals(CONSTANT.NO)) {
                BidirectionalLinks(way);
            }
            else if (dir.equals(CONSTANT.YES)){
                ForwardLinks(way);
            }
            else{
                BackwardLinks(way);
            }

        }
    }


    public void ForwardLinks(Way way) {
        for (int idx = 0 ; idx < way.numberOFNodes()-1 ; idx++) {
            ForwardEdge(way.getNode(idx) , way.getNode(idx+1) , way.getId());
        }
    }

    public void BackwardLinks(Way way) {
        for (int idx = ((int)way.numberOFNodes())-1 ; idx >0 ; idx--) {
            ForwardEdge(way.getNode(idx) , way.getNode(idx-1), way.getId());
        }
    }

    public void BidirectionalLinks(Way way) {
        for (int idx = 0 ; idx < way.numberOFNodes()-1 ; idx++) {
            BidirectionalEdge(way.getNode(idx) , way.getNode(idx+1) , way.getId());
        }
    }


    public void ForwardEdge(int source , int target,int wayId) {
        Edge edge = edgeProcessor.createEdge(graph.getNode(source) , graph.getNode(target) , graph.getWay(wayId), graph , graph.getEdgeCount());
        graph.addOutEdge(source, edge.getId());
        graph.addInEdge(target , edge.getId());
    }

    public void BidirectionalEdge(int source , int target,int WayId) {
        ForwardEdge(source,target,WayId);
        ForwardEdge(target,source,WayId);
    }

}
