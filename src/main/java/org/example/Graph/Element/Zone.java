package org.example.Graph.Element;

import org.example.Graph.Element.ElementUtils.SpatialObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class Zone {

    private List<Point> points;
    private Point center;
    private String name;
    private int parentId = 0;
    private List<Zone> subZones;
    public Zone(List<Point> points, String name , Point center) {
        this.points = points;
        this.name = name;
        this.center = center;
        subZones = new ArrayList<Zone>();
    }


    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Zone> getSubZones() {
        return subZones;
    }

    public void setSubZones(List<Zone> subZones) {
        this.subZones = subZones;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "name='" + name + '\'' +
                ", center=" + center +
                ", parentId=" + parentId +
                '}';
    }
}
