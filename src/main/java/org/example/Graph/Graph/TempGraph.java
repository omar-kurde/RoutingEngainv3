package org.example.Graph.Graph;


import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Place;
import org.example.Graph.Element.Way;
import org.example.util.utilObjects.ExtendingList;
import org.example.util.utilObjects.ExtendingMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class TempGraph implements Graph {
    private final List<Node> nodes;
    private final List<Way> ways;
    private final List<Edge> edges;
    private final List<Place> places;
    private Map<Long, List<Long>> virtualNextEdges;
    private Map<Long, List<Long>> virtualPrevEdges;


    private final AtomicLong nodeCount;
    private final AtomicLong wayCount;
    private final AtomicLong edgeCount;


    public TempGraph(Graph graph) {
        nodes = new ExtendingList<>(graph.NODES_LIST());
        ways = new ExtendingList<>(graph.WAYS_LIST());
        edges = new ExtendingList<>(graph.EDGES_LIST());
        virtualNextEdges = new ExtendingMap<>(graph.NEXT_EDGES_LIST());
        virtualPrevEdges = new ExtendingMap<>(graph.PREV_EDGES_LIST());
        places = new ExtendingList<>(graph.PLACES_LIST());
        nodeCount = new AtomicLong(graph.getNodeCount().get());
        wayCount = new AtomicLong(graph.getWayCount().get());
        edgeCount = new AtomicLong(graph.getEdgeCount().get());

    }

    @Override
    public List<Node> NODES_LIST() {
        return this.nodes;
    }
    @Override
    public List<Way> WAYS_LIST() {
        return this.ways;
    }

    @Override
    public Map<Long, List<Long>> NEXT_EDGES_LIST() {
        return this.virtualNextEdges;
    }

    @Override
    public Map<Long, List<Long>> PREV_EDGES_LIST() {
        return this.virtualPrevEdges;
    }

    @Override
    public List<Place> PLACES_LIST() {
        return places;
    }

    @Override
    public AtomicLong getNodeCount() {
        return nodeCount;
    }

    @Override
    public AtomicLong getEdgeCount() {
        return edgeCount;
    }

    @Override
    public AtomicLong getWayCount() {
        return wayCount;
    }

    @Override
    public List<Edge> EDGES_LIST(){
        return this.edges;
    }


    @Override
    public List<Long> prevEdges(Long nodeId) {
        if (nodeId>=this.NODES_LIST().size() || nodeId<0)  {
            return new ArrayList<>();
        }
//        int index = nodeId.intValue();
        return this.virtualPrevEdges.getOrDefault(nodeId , new ArrayList<>());
//        return nodes.get(index).getInEdges();
    }

    @Override
    public List<Long> nextEdges(Long nodeId) {
        if (nodeId>=this.NODES_LIST().size()  || nodeId<0 ) {
            return new ArrayList<>();
        }
//        int index = nodeId.intValue();
        return this.virtualNextEdges.getOrDefault(nodeId , new ArrayList<>());
//        return nodes.get(index).getOutEdges();
    }


    @Override
    public List<Long> nextNodes(Long nodeId) {
        List<Long> nextnodes  = new ArrayList<>();
        if (nodeId>=this.NODES_LIST().size() || nodeId<0) {
            return nextnodes;
        }
        for (Long edge : this.nextEdges(nodeId)) {
            nextnodes.add(this.edges.get(edge.intValue()).getTailId());
        }
        return nextnodes;
    }
    @Override
    public List<Long> prevNodes(Long nodeId){

        List<Long> prevnodes = new ArrayList<>();
        if (nodeId>=this.NODES_LIST().size() || nodeId<0) {
            return prevnodes;
        }
        for (Long edge : this.prevEdges(nodeId)) {
            prevnodes.add(this.edges.get(edge.intValue()).getHeadId());
        }
        return prevnodes;
    }


    @Override
    public Node getNode(Long id){
        return nodes.get(id.intValue());
    }
    @Override
    public Edge getEdge(Long id){
        return edges.get(id.intValue());
    }

    @Override
    public Way getWay(Long id) {
        return ways.get(id.intValue());
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public void addPlace(Place place) {
        places.add(place);
    }

    @Override
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public void addWay(Way way) {
        ways.add(way);
    }

    @Override
    public void addOutEdge(Long nodeId, Long edgeId) {
        this.virtualNextEdges.computeIfAbsent(nodeId, k->new ArrayList<>()).add(edgeId);
    }

    @Override
    public void addInEdge(Long nodeId, Long edgeId) {
        this.virtualPrevEdges.computeIfAbsent(nodeId, k->new ArrayList<>()).add(edgeId);
    }


}
