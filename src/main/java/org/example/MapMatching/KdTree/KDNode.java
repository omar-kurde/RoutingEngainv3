package org.example.MapMatching.KdTree;

import org.example.Graph.Element.Node;

public class KDNode implements Comparable<KDNode> {
    private Long id;
    private KDPoint point;
    private KDNode left , right;
    private Node node;
    public KDNode(KDPoint point) {
        this.point = point;
    }
    public KDNode(Node node) {
        this.point = new KDPoint(node.getLat() , node.getLon());
        this.node = node;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    @Override
    public int compareTo( KDNode other) {
        for (int d = 0 ; d < 2 ; d++){
            if (this.point.getAxis(d) < other.point.getAxis(d))
                return -1;
            else if (this.point.getAxis(d) > other.point.getAxis(d)){
                return 1;
            }
        }
        return 0;
    }

    public KDPoint getPoint() {
        return point;
    }

    public KDNode getLeft() {
        return left;
    }

    public KDNode getRight() {
        return right;
    }

    public Node getNode() {
        return node;
    }

    public void setPoint(KDPoint point) {
        this.point = point;
    }

    public void setLeft(KDNode left) {
        this.left = left;
    }

    public void setRight(KDNode right) {
        this.right = right;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
