package org.example.Graph.Graph;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.example.Graph.Element.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NormalGraph implements Graph {
    private final List<Node> nodes;
    private final List<Way> ways;
    private final List<Edge> edges;
    private final List<Place> places;
    private final List<Zone> zones;
    private Map<Integer, IntArrayList> nextEdges;
    private Map<Integer, IntArrayList> prevEdges;
    private final AtomicInteger nodeCount = new AtomicInteger(0);
    private final AtomicInteger wayCount = new AtomicInteger(0);
    private final AtomicInteger edgeCount = new AtomicInteger(0);
    // edge , node  ,  i will create fake next nodes and fake next edges so this good ayayyayaay
    public NormalGraph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.ways = new ArrayList<>();
        this.places = new ArrayList<>();
        this.zones = new ArrayList<>();
        this.nextEdges = new HashMap<>();
        this.prevEdges = new HashMap<>();
    }

    @Override
    public List<Node> NODES_LIST() {
        return this.nodes;
    }
    @Override
    public List<Edge> EDGES_LIST(){
        return this.edges;
    }

    @Override
    public List<Way> WAYS_LIST() {
        return this.ways;
    }
    @Override
    public List<Place> PLACES_LIST() {
        return this.places;
    }

    @Override
    public List<Zone> ZONES_LIST() {
        return this.zones;
    }
    @Override
    public Map<Integer, IntArrayList> NEXT_EDGES_LIST() {
        return this.nextEdges;
    }

    @Override
    public Map<Integer , IntArrayList> PREV_EDGES_LIST() {
        return this.prevEdges;
    }


    @Override
    public AtomicInteger getNodeCount() {
        return nodeCount;
    }

    @Override
    public AtomicInteger getEdgeCount() {
        return wayCount;
    }

    @Override
    public AtomicInteger getWayCount() {
        return edgeCount;
    }



    @Override
    public IntArrayList prevEdges(int nodeId) {
        if (nodeId>=this.NODES_LIST().size() || nodeId<0)  {
            return new IntArrayList();
        }
        return this.prevEdges.getOrDefault(nodeId , new IntArrayList());
    }

    @Override
    public IntArrayList nextEdges(int nodeId) {
        if (nodeId>=this.NODES_LIST().size()  || nodeId<0 ) {
            return new IntArrayList();
        }
        return this.nextEdges.getOrDefault(nodeId , new IntArrayList());
    }

    @Override
    public IntArrayList nextNodes(int nodeid) {
        IntArrayList nextnodes  = new IntArrayList();
        if (nodeid>=this.NODES_LIST().size() || nodeid<0) {
            return nextnodes;
        }
        for (int edge : this.nextEdges(nodeid)) {
            nextnodes.add(this.edges.get((int)edge).getTailId());
        }
        return nextnodes;
    }
    @Override
    public IntArrayList prevNodes(int nodeid){

        IntArrayList prevnodes = new IntArrayList();
        if (nodeid>=this.NODES_LIST().size() || nodeid<0) {
            return prevnodes;
        }
        for (int edge : this.prevEdges(nodeid)) {
            prevnodes.add(this.edges.get((int)edge).getHeadId());
        }
        return prevnodes;
    }


    @Override
    public Node getNode(int id){

        return nodes.get((int)id);
    }
    @Override
    public Edge getEdge(int id){
        return edges.get((int)id);
    }

    @Override
    public Way getWay(int id) {
        return ways.get((int)id);
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
    public void addZone(Zone zone) {
        this.zones.add(zone);
    }

    @Override
    public void addOutEdge(int nodeId, int edgeId) {
        this.nextEdges.computeIfAbsent(nodeId, k->new IntArrayList()).add(edgeId);
    }

    @Override
    public void addInEdge(int nodeId, int edgeId) {
        this.prevEdges.computeIfAbsent(nodeId, k->new IntArrayList()).add(edgeId);
    }
}
