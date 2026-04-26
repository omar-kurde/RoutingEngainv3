package org.example.MapMatching.RTree;


import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;
import org.example.Graph.Graph.Graph;
import org.example.Graph.Graph.NormalGraph;
import org.example.MapMatching.NearestEdgeFinder;

import java.util.ArrayList;
import java.util.List;


public class Rtree implements NearestEdgeFinder {
    private RNode root;
    private final Graph graph;
    private final REdgeFactory redgeFactory;
    private final int Min = 2 , Max =4;
    public Rtree(Graph graph) {
        this.graph = graph;
        this.redgeFactory = new REdgeFactory(graph);
        this.root = new leaf(Min,Max , null);
    }
    public void init(){
        List<SpatialObject> spatialObjects = new ArrayList<>();
        for (Node node : graph.NODES_LIST()){
            if (!node.isOnWay()){
                continue;
            }
            for (Long NextEdge : graph.nextEdges(node.getId())){
                Edge edge = graph.getEdge(NextEdge);
                SpatialObject street = redgeFactory.create(edge);
                spatialObjects.add(street);
//                insert(street);
            }
        }
        this.root = new RtreeBuilder(Min, Max).build(spatialObjects);
        System.out.println("OMAR");

    }

    public void insert(SpatialObject e){
        RNode newNode = this.root.insert(e);
        if (newNode != null){ // add root
            branch newroot = new branch(Min,Max , null);
            newroot.Add_object(newNode);
            newroot.Add_object(root);
            newNode.SetParent(newroot);
            root.SetParent(newroot);
            root = newroot;
        }
    }



    public SpatialObject search(RPoint point){
        return search(point , new DoubleRef(Double.MAX_VALUE));
    }
    protected SpatialObject search(RPoint point , DoubleRef dis) {
        return search(point,dis , 0D);
    }

    protected SpatialObject search(RPoint point , DoubleRef dis , Double mn) {
        return root.search(point,dis ,mn);
    }

//    @Override
//    public Long ClosestNode(Double lat1 , Double lon1, Graph graph2){
//        REdge edge = (REdge) search(new RPoint(lat1 , lon1));
//        RPoint p = (RPoint) closestPointOnLine(edge.start,  edge.end, new RPoint(lat1 , lon1));
//        Long id=graph2.AddNode((REdge) edge, p);
//        return id;
//    }
    public List<REdge> ClosestEdges(Double lat1, Double lon1, int count){
        DoubleRef mx = new DoubleRef(Double.MAX_VALUE);
        Double mn=0D;
        List<REdge> l =new ArrayList<>();
        for (int i=1;i<=count;i++){
            l.add((REdge) search(new RPoint(lat1 , lon1) , mx , mn));
            mn=mx.value;
            mx.value = Double.MAX_VALUE;
        }
        return l;
    }


    @Override
    public Edge nearestEdge(Point point) {
        REdge edge = (REdge) search(new RPoint(point.GetLat() , point.GetLon()));
        System.out.println(edge);
        return edge.getEdge();
    }
}
