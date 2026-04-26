package org.example.MapMatching.KdTree;

public class KDPoint implements Comparable<KDPoint> {
    Double[] coordinates;


    public KDPoint(Double... coordinates) {
        this.coordinates = coordinates;
    }

    public Double getAxis(int axis) {
        return coordinates[axis];
    }

    public Double distance(KDPoint other) {
        Double sum = 0D;
        for (int i = 0; i < coordinates.length; i++) {
            Double diff = coordinates[i] - other.coordinates[i];
            sum += diff * diff;
        }
        return sum;
    }

    @Override
    public int compareTo(KDPoint other) {
        for (int d = 0; d < 2; d++) {
            if (this.getAxis(d) < other.getAxis(d))
                return -1;
            else if (this.getAxis(d) > other.getAxis(d)) {
                return 1;
            }
        }
        return 0;
    }

}
