package org.example.MapMatching.RTree;

public interface SpatialObject {

    Rectangle getMBR();
    RPoint center();
    double latCenter();
    double lonCenter();
//    Long GetStartNodeId();
//    Long GetEndNodeId();
//


}
