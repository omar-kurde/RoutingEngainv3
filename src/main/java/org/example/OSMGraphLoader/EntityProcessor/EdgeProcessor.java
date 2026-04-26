package org.example.OSMGraphLoader.EntityProcessor;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Way;
import org.example.Graph.Graph.Graph;
import org.example.util.Math.Geo;

import java.util.concurrent.atomic.AtomicLong;

public class EdgeProcessor {

    public Edge createEdge(Node source  , Node target, Way way , Graph graph, AtomicLong counter) {

        Edge newEdge = new Edge.Builder()
                .id(counter.getAndIncrement())
                .wayId(way.getID())
                .HeadId(source.getId())
                .tailId(target.getId())
                .weight(getCost(source , target))
                .isHighWay(way.isHighWay())
                .oneWay(way.isOneWay())
                .build();

        graph.addEdge(newEdge);

        return newEdge;
    }

    private Double getCost(Node source  , Node target){
        Double lat1 = source.getLat();
        Double lon1 = source.getLon();
        Double lat2 = target.getLat();
        Double lon2 = target.getLon();
        return Geo.GetEdgeCost(lat1, lon1, lat2, lon2);
    }

}
