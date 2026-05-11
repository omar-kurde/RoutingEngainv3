package org.example.Routing;

import org.example.Graph.Graph.Graph;
import org.example.ServiceResponse.RoutingPath;

public interface Routing {
    RoutingPath shortestPath(int source , int target , Graph graph);
}
