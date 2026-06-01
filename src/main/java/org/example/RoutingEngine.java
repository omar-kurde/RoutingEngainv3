package org.example;

import org.example.Graph.Element.*;
import org.example.Graph.Graph.Graph;
import org.example.MapMatching.KdTree.KDTree;
import org.example.MapMatching.MapMatching;
import org.example.MapMatching.MapMatchingStrategy;
import org.example.MapMatching.RTree.Rtree;
import org.example.OSMGraphLoader.GraphLoader;
import org.example.PlacesService.H3.H3PlaceService;
import org.example.PlacesService.H3.H3PlaceWrapper;
import org.example.ServiceRequest.MapMatchingResult;
import org.example.ServiceResponse.RoutingPath;
import org.example.Services.MapMatchingService;
import org.example.Services.PlaceService;
import org.example.Services.RoutingService;

import java.util.List;

public class RoutingEngine {

    private GraphLoader graphLoader = new GraphLoader();
    private Graph graph;
    private final int RESOLUTION = 7;
    private final int K = 3;

    private MapMatching mapMatchingStrategy;

    private MapMatchingService mapMatchingService;
    private PlaceService placeService;
    private RoutingService routingService;

    public void start() throws Exception {
        this.graph = graphLoader.loadGraph();

        System.out.println(this.graph.ZONES_LIST().get(7).getPoints().size());
        KDTree kdTree = new KDTree(graph);
        kdTree.init();


        Rtree rtree = new Rtree(graph);
        rtree.init();

        this.placeService = new PlaceService(RESOLUTION , K);
        placeService.addPlaces(graph.PLACES_LIST());

        mapMatchingStrategy = new MapMatchingStrategy(kdTree , rtree);
        mapMatchingService = new MapMatchingService(graph, mapMatchingStrategy);

        this.routingService = new RoutingService(mapMatchingService , graph);
        System.out.println(graph.ZONES_LIST());
    }

    public RoutingPath shortestRoute(double startLat, double startLon , double endLat, double endLon) {
        return routingService.shortestRoute(new Point(startLat ,startLon) , new Point(endLat ,endLon));
    }
    public List<H3PlaceWrapper> getNearby(double lat, double lon , PlaceCategory category) {
        return placeService.getNearby(lat , lon , category);
    }

    public Node MatchToNode(double lat ,double lon) {
        return mapMatchingService.MatchToNode(new Point(lat , lon));
    }

    public MapMatchingResult MatchToEdge(double lat ,double lon) {
        return mapMatchingService.MatchToEdge(new Point(lat , lon));
    }

    public Zone getZone(String name) {
        for (Zone zone : graph.ZONES_LIST()) {
            if (zone.getName().equals(name)) {
                return zone;
            }
        }
        return null;
    }
    public List<Zone> getAllZones() {
        return graph.ZONES_LIST();
    }

}



