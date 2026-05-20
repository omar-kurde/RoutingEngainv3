package org.example.Graph.Graph;


import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.example.Graph.Element.*;
import org.example.util.utilObjects.ExtendingList;
import org.example.util.utilObjects.ExtendingMap;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TempGraph implements Graph {
    private final List<Node> nodes;
    private final List<Way> ways;
    private final List<Edge> edges;
    private final List<Place> places;
    private final List<Zone> zones;
    private Map<Integer, IntArrayList> virtualNextEdges;
    private Map<Integer, IntArrayList> virtualPrevEdges;


    private final AtomicInteger nodeCount;
    private final AtomicInteger wayCount;
    private final AtomicInteger edgeCount;


    public TempGraph(Graph graph) {
        nodes = new ExtendingList<>(graph.NODES_LIST());
        ways = new ExtendingList<>(graph.WAYS_LIST());
        edges = new ExtendingList<>(graph.EDGES_LIST());
        places = new ExtendingList<>(graph.PLACES_LIST());
        zones = new ExtendingList<>(graph.ZONES_LIST());

        virtualNextEdges = new ExtendingMap<>(graph.NEXT_EDGES_LIST() , (list1, list2) -> {
            IntArrayList merged = new IntArrayList(list1);
            merged.addAll(list2);
            return  merged;
        });
        virtualPrevEdges = new ExtendingMap<>(graph.PREV_EDGES_LIST() , (list1, list2) -> {
            IntArrayList merged = new IntArrayList(list1);
            merged.addAll(list2);
            return  merged;
        });
        nodeCount = new AtomicInteger(graph.getNodeCount().get());
        wayCount = new AtomicInteger(graph.getWayCount().get());
        edgeCount = new AtomicInteger(graph.getEdgeCount().get());

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
        return places;
    }

    @Override
    public List<Zone> ZONES_LIST() {
        return zones;
    }

    @Override
    public Map<Integer, IntArrayList> NEXT_EDGES_LIST() {
        return this.virtualNextEdges;
    }

    @Override
    public Map<Integer, IntArrayList> PREV_EDGES_LIST() {
        return this.virtualPrevEdges;
    }

    @Override
    public AtomicInteger getNodeCount() {
        return nodeCount;
    }

    @Override
    public AtomicInteger getEdgeCount() {
        return edgeCount;
    }

    @Override
    public AtomicInteger getWayCount() {
        return wayCount;
    }


    @Override
    public IntArrayList prevEdges(int nodeId) {
        if (nodeId>=this.NODES_LIST().size() || nodeId<0)  {
            return new IntArrayList();
        }
//        int index = nodeId.intValue();
        return this.virtualPrevEdges.get(nodeId);
//        return nodes.get(index).getInEdges();
    }

    @Override
    public IntArrayList nextEdges(int nodeId) {
        if (nodeId>=this.NODES_LIST().size()  || nodeId<0 ) {
            return new IntArrayList();
        }
//        int index = nodeId.intValue();
        return this.virtualNextEdges.get(nodeId);
//        return nodes.get(index).getOutEdges();
    }


    @Override
    public IntArrayList nextNodes(int nodeId) {
        IntArrayList nextnodes  = new IntArrayList();
        if (nodeId>=this.NODES_LIST().size() || nodeId<0) {
            return nextnodes;
        }
        for (int edge : this.nextEdges(nodeId)) {
            nextnodes.add(this.edges.get(edge).getTailId());
        }
        return nextnodes;
    }
    @Override
    public IntArrayList prevNodes(int nodeId){

        IntArrayList prevnodes = new IntArrayList();
        if (nodeId>=this.NODES_LIST().size() || nodeId<0) {
            return prevnodes;
        }
        for (int edge : this.prevEdges(nodeId)) {
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
        this.virtualNextEdges.computeIfAbsent(nodeId, k->new IntArrayList()).add(edgeId);
    }

    @Override
    public void addInEdge(int nodeId, int edgeId) {
        this.virtualPrevEdges.computeIfAbsent(nodeId, k->new IntArrayList()).add(edgeId);
    }


}
