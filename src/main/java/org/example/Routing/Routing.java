package org.example.Routing;

import org.example.Graph.Graph.Graph;
import org.example.ServiceResponse.RoutingPath;

public interface Routing {
    RoutingPath shortestPath(Long source , Long target , Graph graph);
}
