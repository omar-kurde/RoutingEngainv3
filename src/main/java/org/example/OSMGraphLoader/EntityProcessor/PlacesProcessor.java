package org.example.OSMGraphLoader.EntityProcessor;

import org.example.Graph.Element.*;
import org.example.Graph.Element.ElementUtils.UnifiedNames;
import org.example.Graph.Graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.CONSTANT.*;

public class PlacesProcessor {

    public Place process(Node node , Graph graph) {
        List<Point> points = new ArrayList<>();
        points.add(node.getPoint());
        if (node.getTag(AMENITY) != null){
            String amenity = node.getTag(AMENITY);
            if (UnifiedNames.contain(amenity)){
                PlaceCategory cat = UnifiedNames.get(amenity);
                String name = getName(node);
                Place place = new Place(points, name , cat);
                graph.addPlace(place);
                return place;
            }
        }
        else if(node.getTag(SHOP) != null){
            String shop = node.getTag(SHOP);
            if (UnifiedNames.contain(shop)){
                PlaceCategory cat = UnifiedNames.get(shop);
                String name = getName(node);
                Place place = new Place(points, name , cat);
                graph.addPlace(place);
                return place;
            }
        }
        return null;
    }



    public Place process(Way way , Graph graph) {
        List<Point> points = way.getNodes().stream().map(id->graph.getNode(id).getPoint()).collect(Collectors.toList());
        if (way.getTag(AMENITY) != null){
            String amenity = way.getTag(AMENITY);
            if (UnifiedNames.contain(amenity)){
                PlaceCategory cat = UnifiedNames.get(amenity);
                String name = getName(way);
                Place place = new Place(points, name , cat);
                graph.addPlace(place);
                return place;
            }
        }
        else if(way.getTag(SHOP) != null){
            String shop = way.getTag(SHOP);
            if (UnifiedNames.contain(shop)){
                PlaceCategory cat = UnifiedNames.get(shop);
                String name = getName(way);
                Place place = new Place(points, name , cat);
                graph.addPlace(place);
                return place;            }
        }
        else if(way.getTag(BUILDING) != null){
            String building = way.getTag(BUILDING);
            if (UnifiedNames.contain(building)){
                PlaceCategory cat = UnifiedNames.get(building);
                String name = getName(way);
                Place place = new Place(points, name , cat);
                graph.addPlace(place);
                return place;
            }
        }
        return null;
    }


    private String getName(Node node){
        if (node.getTag("name")!=null){
            return node.getTag("name");}
        else if (node.getTag("name:ar")!=null)
            return node.getTag("name:ar");
        else if(node.getTag("name:en")!=null)
            return node.getTag("name:en");
        return null;
    }

    private String getName(Way way){
        if (way.getTag("name")!=null){
            return way.getTag("name");
        }
        else if (way.getTag("name:ar")!=null)
            return way.getTag("name:ar");
        else if(way.getTag("name:en")!=null)
            return way.getTag("name:en");
        return null;
    }
}
