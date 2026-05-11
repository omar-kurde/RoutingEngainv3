package org.example.PlacesService.H3;

import org.example.Graph.Element.Place;
import org.example.Graph.Element.PlaceCategory;
import org.example.Graph.Element.Point;

import java.util.List;

public class H3PlaceWrapper {
    private long h3Index;
    private Place place;
    public H3PlaceWrapper(long h3Index, Place place) {
        this.h3Index = h3Index;
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }
    public long getH3Index() {
        return h3Index;
    }

    public List<Point> getPoints() {
        return this.place.getPoints();
    }
    public Point getCenter() {
        return this.place.getCenter();
    }


    public PlaceCategory getCategory() {
        return this.place.getCategory();
    }

    public String getName() {
        return this.place.getName();
    }


    @Override
    public String toString() {
        return "H3PlaceWrapper{" +
                "h3Index=" + h3Index +
                ", place=" + place +
                '}';
    }
}
