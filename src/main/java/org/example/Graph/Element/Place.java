package org.example.Graph.Element;

import org.example.Graph.Element.ElementUtils.SpatialObjectUtil;

import java.util.List;

public class Place  implements PlacesSpatialObject{

    private List<Point> points;
    private Point center;
    private String name;
    private PlaceCategory category;
    public Place(List<Point> points , String name , PlaceCategory category) {
        this.points = points;
        this.name = name;
        this.category = category;
        this.center = SpatialObjectUtil.center(points);
    }
    @Override
    public List<Point> getPoints() {
        return this.points;
    }
    @Override
    public Point getCenter() {
        return this.center;
    }


    public PlaceCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
