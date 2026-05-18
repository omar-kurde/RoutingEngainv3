package org.example.Graph.Graph;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.example.Graph.Element.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicInteger;

public interface Graph {
     List<Node> NODES_LIST();
     List<Edge> EDGES_LIST();
     List<Way> WAYS_LIST();
     List<Place> PLACES_LIST();
     List<Zone> ZONES_LIST();

     Map<Integer , IntArrayList> NEXT_EDGES_LIST();
     Map<Integer, IntArrayList> PREV_EDGES_LIST();

     AtomicInteger getNodeCount();
     AtomicInteger getEdgeCount();
     AtomicInteger getWayCount();
     Node getNode(int id);
     Edge getEdge(int id);
     Way getWay(int id);
     void addNode(Node node);
     void addPlace(Place place);
     void addEdge(Edge edge);
     void addWay(Way way);
     void addZone(Zone zone);
//     void addNextNode(int nodeId , int nextNodeId);
//     void addPrevNode(int nodeId , int nextNodeId);
     void addOutEdge(int nodeId , int edgeId);
     void addInEdge(int nodeId , int edgeId);


     IntArrayList nextEdges(int nodeId);
     IntArrayList nextNodes(int nodeId);
     IntArrayList prevEdges(int nodeId);
     IntArrayList prevNodes(int nodeId);
}
