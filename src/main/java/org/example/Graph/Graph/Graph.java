package org.example.Graph.Graph;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Place;
import org.example.Graph.Element.Way;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public interface Graph {
     List<Node> NODES_LIST();
     List<Edge> EDGES_LIST();
     List<Way> WAYS_LIST();
     Map<Long ,List<Long>> NEXT_EDGES_LIST();
     Map<Long ,List<Long>> PREV_EDGES_LIST();

    List<Place> PLACES_LIST();
     AtomicLong getNodeCount();
     AtomicLong getEdgeCount();
     AtomicLong getWayCount();
     Node getNode(Long id);
     Edge getEdge(Long id);
     Way getWay(Long id);
     void addNode(Node node);
     void addPlace(Place place);
     void addEdge(Edge edge);
     void addWay(Way way);
//     void addNextNode(Long nodeId , Long nextNodeId);
//     void addPrevNode(Long nodeId , Long nextNodeId);
     void addOutEdge(Long nodeId , Long edgeId);
     void addInEdge(Long nodeId , Long edgeId);



    List<Long> nextEdges(Long nodeId);
     List<Long> nextNodes(Long nodeId);
     List<Long> prevEdges(Long nodeId);
     List<Long> prevNodes(Long nodeId);
}
