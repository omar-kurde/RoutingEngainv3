package org.example;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.PlaceCategory;
import org.example.Graph.Element.Point;
import org.example.Graph.Graph.Graph;
import org.example.MapMatching.KdTree.KDTree;
import org.example.MapMatching.RTree.Rtree;
import org.example.OSMGraphLoader.GraphLoader;
import org.example.PlacesService.H3.H3PlaceService;
import org.example.PlacesService.H3.H3PlaceWrapper;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        RoutingEngine engine = new RoutingEngine();
        engine.start();

        System.out.println(engine.shortestRoute(31.972586435132644 , 35.824091625747954 , 31.971507926257036 , 35.82408626519647));

        System.out.println();

    }
}
//31.992978790905518
//startLng
//:
//        35.9006140397828

//endLat
//:
//        31.971507926257036
//endLng
//:
//        35.82408626519647
//startLat
//:
//        31.972586435132644
//startLng
//:
//        35.824091625747954