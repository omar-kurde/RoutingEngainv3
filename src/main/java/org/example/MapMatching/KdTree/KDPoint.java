package org.example.MapMatching.KdTree;

public class KDPoint implements Comparable<KDPoint> {
    double[] coordinates;


    public KDPoint(double... coordinates) {
        this.coordinates = coordinates;
    }

    public double getAxis(int axis) {
        return coordinates[axis];
    }

    public double distance(KDPoint other) {
        double sum = 0D;
        for (int i = 0; i < coordinates.length; i++) {
            double diff = coordinates[i] - other.coordinates[i];
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
