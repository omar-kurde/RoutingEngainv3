package org.example.ServiceResponse;

public class PathNode {
    private int id;
    private double latitude;
    private double longitude;
    private int order;

    public PathNode(int id, double latitude, double longitude, int order) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "PathNode{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", order=" + order +
                '}';
    }
}
