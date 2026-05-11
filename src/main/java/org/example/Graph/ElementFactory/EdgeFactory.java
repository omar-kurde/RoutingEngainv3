package org.example.Graph.ElementFactory;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Way;
import org.example.Graph.Graph.Graph;
import org.example.util.Math.Geo;


public class EdgeFactory {

//    public Edge createEdge() {
//
//        Edge newEdge = new Edge.Builder()
//                .id(counter.getAndIncrement())
//                .wayId(way.getID())
//                .HeadId(source.getID())
//                .tailId(target.getID())
//                .weight(getCost(source , target))
//                .isHighWay(way.isHighWay())
//                .oneWay(way.isOneWay())
//                .build();
//
//        graph.addEdge(newEdge);
//
//        return newEdge;
//    }

    private static double getCost(Node source  , Node target){
        double lat1 = source.getLat();
        double lon1 = source.getLon();
        double lat2 = target.getLat();
        double lon2 = target.getLon();
        return Geo.GetEdgeCost(lat1, lon1, lat2, lon2);
    }

    public static void createSubEdge(Edge edge , Node node , Graph graph){
        createOutForwordEdge(edge, node, graph);
        createInForwordEdge(edge, node, graph);
        if (!edge.isOneWay()) {
            createOutBacWordEdge(edge, node, graph);
            createInBacWordEdge(edge, node, graph);
        }
    }
    private static void createOutForwordEdge( Edge edge , Node node , Graph graph){
        Node edgeEndNode = graph.getNode(edge.getTailId());
       Edge newEdge = new Edge.Builder()
               .id(graph.getEdgeCount().getAndIncrement())
               .HeadId(node.getId())
               .tailId(edgeEndNode.getId())
               .isHighWay(edge.IsHighWay())
               .wayId(edge.getWayId())
               .oneWay(edge.isOneWay())
               .weight(getCost(edgeEndNode , node))
               .build();
       graph.addEdge(newEdge);
//       node.addOutEdge(newEdge.getId());
       graph.addOutEdge(node.getId() , newEdge.getId());
       graph.addInEdge(edgeEndNode.getId() , newEdge.getId());
    }
    private static void createOutBacWordEdge(Edge edge , Node node , Graph graph){
        Node edgeStartNode = graph.getNode(edge.getHeadId());
        Edge newEdge = new Edge.Builder()
                .id(graph.getEdgeCount().getAndIncrement())
                .HeadId(node.getId())
                .tailId(edgeStartNode.getId())
                .isHighWay(edge.IsHighWay())
                .wayId(edge.getWayId())
                .oneWay(edge.isOneWay())
                .weight(getCost(edgeStartNode , node))
                .build();
        graph.addEdge(newEdge);
//        node.addOutEdge(newEdge.getId());
        graph.addOutEdge(node.getId() , newEdge.getId());
        graph.addInEdge(edgeStartNode.getId() , newEdge.getId());

    }

    private static void createInForwordEdge( Edge edge , Node node , Graph graph){
        Node edgeStartNode = graph.getNode(edge.getHeadId());
        Edge newEdge = new Edge.Builder()
                .id(graph.getEdgeCount().getAndIncrement())
                .HeadId(edgeStartNode.getId())
                .tailId(node.getId())
                .isHighWay(edge.IsHighWay())
                .wayId(edge.getWayId())
                .oneWay(edge.isOneWay())
                .weight(getCost(edgeStartNode , node))
                .build();
        graph.addEdge(newEdge);
        graph.addOutEdge(edgeStartNode.getId() , newEdge.getId());
        graph.addInEdge(node.getId() , newEdge.getId());
    }
    private static void createInBacWordEdge(Edge edge , Node node , Graph graph){
        Node edgeEndNode = graph.getNode(edge.getTailId());
        Edge newEdge = new Edge.Builder()
                .id(graph.getEdgeCount().getAndIncrement())
                .HeadId(edgeEndNode.getId())
                .tailId(node.getId())
                .isHighWay(edge.IsHighWay())
                .wayId(edge.getWayId())
                .oneWay(edge.isOneWay())
                .weight(getCost(edgeEndNode , node))
                .build();
        graph.addEdge(newEdge);
        graph.addOutEdge(edgeEndNode.getId() , newEdge.getId());
        graph.addInEdge(node.getId(), newEdge.getId());

    }

}
