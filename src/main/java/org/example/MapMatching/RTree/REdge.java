package org.example.MapMatching.RTree;


import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Point;

public class REdge implements SpatialObject{

    private Edge edge ;
    private final RPoint start;
    private final RPoint end;
//    Long WayId;

    REdge(Edge edge, RPoint r1, RPoint r2) {
        this.edge = edge;
        this.start = r1;
        this.end = r2;
    }
    public boolean isOneWay(){
        return edge.isOneWay();
    }

    public Long getWayId() {
        return edge.getWayId();
    }

    public Double getWight() {
        return edge.getWight();
    }

    public Long getStartNodeId() {
        return edge.getTailId();
    }

    public Long GetEndNodeId() {
        return edge.getHeadId();
    }

    public Long getId() {
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
