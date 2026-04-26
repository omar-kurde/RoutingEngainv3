package org.example.MapMatching.KdTree;

import java.util.Comparator;

public class KDNodeComparator implements Comparator<KDNode> {
    private final int axis;
    public KDNodeComparator(int axis) {
        this.axis = axis;
    }
    @Override
    public int compare(KDNode o1, KDNode o2) {
        for (int inx = axis ; inx < axis+2 ; inx++) {
            if (o1.getPoint().getAxis(inx%2) < o2.getPoint().getAxis(inx%2))
                return -1;
            if (o1.getPoint().getAxis(inx%2) > o2.getPoint().getAxis(inx%2))
                return 1;
        }
        return 0;    }
}
