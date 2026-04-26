package org.example.MapMatching.RTree;

public interface RNode extends SpatialObject {


    boolean isLeaf();

    RNode getParent();


    RNode insert(SpatialObject spatialObject);
    SpatialObject search(RPoint point , DoubleRef dis);
    SpatialObject search(RPoint point ,DoubleRef dis , Double mn);
    void SetParent(RNode parent);

}
