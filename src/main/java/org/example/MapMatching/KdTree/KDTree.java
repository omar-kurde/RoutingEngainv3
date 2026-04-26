package org.example.MapMatching.KdTree;

import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;
import org.example.Graph.Graph.Graph;
import org.example.MapMatching.NearestNodeFinder;
import org.example.util.Math.Geo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KDTree implements NearestNodeFinder {
    List<KDNode> KdNodes;
    List<KDNode> Xkey , Ykey;
    int dimension=2;
    private final Graph graph;
    private KDNode root;

    public KDTree(Graph graph) {
        this.graph = graph;
        Xkey = new ArrayList<>();
        Ykey = new ArrayList<>();
        KdNodes = new ArrayList<>();
    }

    public void init(){
        Xkey = new ArrayList<>();
        Ykey = new ArrayList<>();
        KdNodes = new ArrayList<>();
        for (Node node : graph.NODES_LIST()){
            if (!node.isOnWay()){
                continue;
            }
            KDNode kdnode = new KDNode(node);
            kdnode.setId(node.getId());
            KdNodes.add(kdnode);
            Xkey.add(kdnode);
            Ykey.add(kdnode);
        }
        Xkey.sort(new KDNodeComparator(0));
        Ykey.sort(new KDNodeComparator(1));
        List<List<KDNode>> temp = new ArrayList<>(Arrays.asList( Xkey,Ykey));
        System.out.println( "ddd" + Xkey.size());
        this.root = buildTree(temp , 0);
    }

    private KDNode buildTree(List<List<KDNode>> nodes, int depth){

        if (nodes.isEmpty() || nodes.get(0).isEmpty()) return null;
        int axis = depth%dimension;
        KDNode medianNode = nodes.get(axis).get(nodes.get(axis).size()/2);
        List<List<KDNode>> left = new ArrayList<>();
        List<List<KDNode>> right = new ArrayList<>();

        left.add(new ArrayList<>());
        left.add(new ArrayList<>());

        right.add(new ArrayList<>());
        right.add(new ArrayList<>());

        for (int ax = 0; ax < dimension; ax++) {
            KDNodeComparator comp = new KDNodeComparator(axis);
            for (KDNode Cur : nodes.get(ax)) {
                if (comp.compare(Cur, medianNode) < 0) {
                    left.get(ax).add(Cur);
                } else if (comp.compare(Cur, medianNode) > 0) {
                    right.get(ax).add(Cur);
                }
            }
        }
        medianNode.setLeft(buildTree(left, (depth + 1) % 2));
        medianNode.setRight(buildTree(right, (depth + 1) % 2));

        return medianNode;

    }

    private KDNode NearestNeighbor(KDNode curnode ,KDPoint query , KDNode best , Double bestDistance , int depth ){
        if (curnode == null)  return best;

        Double curDistance = Geo.GetEdgeCost(
                query.getAxis(0),
                query.getAxis(1),
                curnode.getPoint().getAxis(0),
                curnode.getPoint().getAxis(1));
//        query.Distance(curnode.point);

        if (curDistance < bestDistance) {
            bestDistance = curDistance;
            best = curnode;
        }

        int axis = depth%dimension;

        KDPointComparator comp = new KDPointComparator(axis);

        if (query.compareTo(best.getPoint()) == 0) {
            return best;
        }


        boolean leftFirst = comp.compare(query , curnode.getPoint()) < 0;

        KDNode first = leftFirst ? curnode.getLeft() : curnode.getRight();
        KDNode second = leftFirst ? curnode.getRight() : curnode.getLeft();

        best = NearestNeighbor(first , query , best , bestDistance , (depth+1)%2);
        bestDistance = Geo.GetEdgeCost(
                query.getAxis(0),
                query.getAxis(1),
                best.getPoint().getAxis(0),
                best.getPoint().getAxis(1));


        double avgLat = (query.getAxis(0) + curnode.getPoint().getAxis(0)) / 2.0;
        double axisDiff;
        if (axis == 0) {
            axisDiff = Math.abs(query.getAxis(0) - curnode.getPoint().getAxis(0)) * 111_000; // meters
        } else {
            axisDiff = Math.abs(query.getAxis(1) - curnode.getPoint().getAxis(1)) * 111_000 * Math.cos(Math.toRadians(avgLat));
        }



        if (axisDiff < bestDistance) {
            best = NearestNeighbor(second , query , best , bestDistance , (depth+1)%2);
        }

        return best;
    }


    @Override
    public Node nearestNode(Point point) {
        KDNode kdNode = NearestNeighbor(root ,  new KDPoint(point.getLat() , point.getLon()) , null ,Double.MAX_VALUE , 0);
        return kdNode.getNode();
    }
}
