package org.example.ServiceResponse;

public class PathNode {
    private Long id;
    private double latitude;
    private double longitude;
    private int order;

    public PathNode(Long id, double latitude, double longitude, int order) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
