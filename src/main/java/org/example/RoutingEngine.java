package org.example;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.PlaceCategory;
import org.example.Graph.Element.Point;
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
import org.example.util.Math.Projection;
import org.locationtech.jts.index.kdtree.KdTree;

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

        System.out.println(1);
        KDTree kdTree = new KDTree(graph);
        kdTree.init();

        System.out.println(2);

        Rtree rtree = new Rtree(graph);
        rtree.init();
        System.out.println(3);

        this.placeService = new PlaceService(RESOLUTION , K);
        placeService.addPlaces(graph.PLACES_LIST());

        mapMatchingStrategy = new MapMatchingStrategy(kdTree , rtree);
        mapMatchingService = new MapMatchingService(graph, mapMatchingStrategy);

        this.routingService = new RoutingService(mapMatchingService , graph);
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
}
//package com.smartcity.backend.controller;
//
//import com.smartcity.backend.enums.ReportCategory;
//import com.smartcity.backend.enums.VoteType;
//import org.springframework.web.bind.annotation.*;
//        import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("api/report")
//public class ReportController {
//
//    @PostMapping("/vote")
//    public void voteReport(@RequestParam("reportId") String  reportId , @RequestParam("voteType") VoteType voteType) {
//    }
//
//    @PostMapping("/create")
//    public void createReport(@RequestParam("image") MultipartFile image, @RequestParam("category") ReportCategory category, @RequestParam("description") String description, @RequestParam("lat") double lat, @RequestParam("lon") double lon) {
//
//    }
//
//    @GetMapping("/all")
//    public void getAllReport() {
//
//    }
//}


