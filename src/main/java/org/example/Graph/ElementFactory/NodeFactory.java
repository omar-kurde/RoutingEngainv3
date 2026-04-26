package org.example.Graph.ElementFactory;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;
import org.example.Graph.Graph.Graph;

public class NodeFactory {

    public static Node createVirtualNode(Edge edge , Point point , Graph graph) {
        Node node = new Node.Builder().id(graph.getNodeCount().getAndIncrement()).lat(point.getLat()).lon(point.getLon()).build();
        graph.addNode(node);
        EdgeFactory.createSubEdge(edge , node , graph);
        return node;
    }

}
