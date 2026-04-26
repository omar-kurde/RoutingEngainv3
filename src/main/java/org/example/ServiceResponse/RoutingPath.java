package org.example.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

public class RoutingPath {
    private List<PathNode> pathNodes = new ArrayList<>();
    private double distance;


    public void addPathNode(PathNode node) {
        pathNodes.add(node);
    }
    public List<PathNode> getPathNodes() {
        return pathNodes;
    }
    public void setPathNodes(List<PathNode> pathNodes) {
        this.pathNodes = pathNodes;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "RoutingPath{" +
                "pathNodes=" + pathNodes +
                ", distance=" + distance +
                '}';
    }

}

