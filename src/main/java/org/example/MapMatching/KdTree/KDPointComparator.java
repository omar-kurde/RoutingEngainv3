package org.example.MapMatching.KdTree;

import java.util.Comparator;

public class KDPointComparator implements Comparator<KDPoint>{
    int axis;

    public KDPointComparator(int axis) {
        this.axis = axis;
    }


    @Override
    public int compare(KDPoint o1, KDPoint o2) {
        for (int inx = axis ; inx < axis+o1.coordinates.length ; inx++) {
            if (o1.getAxis(inx%2) < o2.getAxis(inx%2))
                return -1;
            if (o1.getAxis(inx%2) > o2.getAxis(inx%2))
                return 1;
        }
        return 0;

    }

}
