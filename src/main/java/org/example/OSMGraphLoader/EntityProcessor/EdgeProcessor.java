package org.example.OSMGraphLoader.EntityProcessor;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Way;
import org.example.Graph.Graph.Graph;
import org.example.util.Math.Geo;

import java.util.concurrent.atomic.AtomicInteger;

public class EdgeProcessor {

    public Edge createEdge(Node source  , Node target, Way way , Graph graph, AtomicInteger counter) {

        Edge newEdge = new Edge.Builder()
                .id(counter.getAndIncrement())
                .wayId(way.getId())
                .HeadId(source.getId())
                .tailId(target.getId())
                .weight(getCost(source , target))
                .isHighWay(way.isHighWay())
                .oneWay(way.isOneWay())
                .build();

        graph.addEdge(newEdge);

        return newEdge;
    }

    private double getCost(Node source  , Node target){
        double lat1 = source.getLat();
        double lon1 = source.getLon();
        double lat2 = target.getLat();
        double lon2 = target.getLon();
        return Geo.GetEdgeCost(lat1, lon1, lat2, lon2);
    }

}
