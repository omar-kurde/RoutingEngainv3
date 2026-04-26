package org.example.Graph.Graph;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Place;
import org.example.Graph.Element.Way;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class NormalGraph implements Graph {
    private final List<Node> nodes;
    private final List<Way> ways;
    private final List<Edge> edges;
    private final List<Place> places;
    private Map<Long, List<Long>> nextEdges;
    private Map<Long, List<Long>> prevEdges;
    private final AtomicLong nodeCount = new AtomicLong(0);
    private final AtomicLong wayCount = new AtomicLong(0);
    private final AtomicLong edgeCount = new AtomicLong(0);
    // edge , node  ,  i will create fake next nodes and fake next edges so this good ayayyayaay
    public NormalGraph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.ways = new ArrayList<>();
        this.nextEdges = new HashMap<>();
        this.prevEdges = new HashMap<>();
        this.places = new ArrayList<>();
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
        return this.nextEdges;
    }

    @Override
    public Map<Long, List<Long>> PREV_EDGES_LIST() {
        return this.prevEdges;
    }

    @Override
    public List<Place> PLACES_LIST() {
        return this.places;
    }

    @Override
    public AtomicLong getNodeCount() {
        return nodeCount;
    }

    @Override
    public AtomicLong getEdgeCount() {
        return wayCount;
    }

    @Override
    public AtomicLong getWayCount() {
        return edgeCount;
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
        return this.prevEdges.getOrDefault(nodeId , new ArrayList<>());
    }

    @Override
    public List<Long> nextEdges(Long nodeId) {
        if (nodeId>=this.NODES_LIST().size()  || nodeId<0 ) {
            return new ArrayList<>();
        }
        return this.nextEdges.getOrDefault(nodeId , new ArrayList<>());
    }

    @Override
    public List<Long> nextNodes(Long nodeid) {
        List<Long> nextnodes  = new ArrayList<>();
        if (nodeid>=this.NODES_LIST().size() || nodeid<0) {
            return nextnodes;
        }
        for (Long edge : this.nextEdges(nodeid)) {
            nextnodes.add(this.edges.get(edge.intValue()).getTailId());
        }
        return nextnodes;
    }
    @Override
    public List<Long> prevNodes(Long nodeid){

        List<Long> prevnodes = new ArrayList<>();
        if (nodeid>=this.NODES_LIST().size() || nodeid<0) {
            return prevnodes;
        }
        for (Long edge : this.prevEdges(nodeid)) {
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
        this.nextEdges.computeIfAbsent(nodeId, k->new ArrayList<>()).add(edgeId);
    }

    @Override
    public void addInEdge(Long nodeId, Long edgeId) {
        this.prevEdges.computeIfAbsent(nodeId, k->new ArrayList<>()).add(edgeId);
    }
}
