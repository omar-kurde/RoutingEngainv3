package org.example.MapMatching.RTree;


import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Point;

public class REdge implements SpatialObject{

    private Edge edge ;
    private final RPoint start;
    private final RPoint end;
//    int WayId;

    REdge(Edge edge, RPoint r1, RPoint r2) {
        this.edge = edge;
        this.start = r1;
        this.end = r2;
    }
    public boolean isOneWay(){
        return edge.isOneWay();
    }

    public int getWayId() {
        return edge.getWayId();
    }

    public double getWight() {
        return edge.getWight();
    }

    public int getStartNodeId() {
        return edge.getTailId();
    }

    public int GetEndNodeId() {
        return edge.getHeadId();
    }

    public int getId() {
        return edge.getId();
    }



    @Override
    public Rectangle getMBR(){
        return new Rectangle(this.start, this.end);
    }

    @Override
    public RPoint center() {
        double lat = (start.getLat() + end.getLat()) /2;
        double lng = (start.getLon() + end.getLon()) /2;
        return new RPoint(lat,lng);
    }

    @Override
    public double latCenter() {
        return (start.getLat() + end.getLat()) /2;
    }

    @Override
    public double lonCenter() {
        return  (start.getLon() + end.getLon()) /2;
    }

    public Edge getEdge() {
        return edge;
    }

    public RPoint getStart() {
        return start;
    }

    public RPoint getEnd() {
        return end;
    }
}
