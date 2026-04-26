package org.example.Routing.DijkstraRouting;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Graph.Graph;
import org.example.Routing.Routing;
import org.example.ServiceResponse.PathNode;
import org.example.ServiceResponse.RoutingPath;

import java.util.*;

public class DijkstraRouting implements Routing {

    @Override
    public RoutingPath shortestPath(Long sorces , Long target , Graph graph) {
        HashSet<Long> visited = new HashSet<>();
        HashMap<Long,Long> parents = new HashMap<>();
        HashMap<Long,Double> distances = new HashMap<>();
        PriorityQueue<Long> Queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        Queue.add(sorces);
        distances.put(sorces,0.0);

        while (!Queue.isEmpty()) {
            Long Current = Queue.poll();

            if (visited.contains(Current)) {
                continue;
            }
            visited.add(Current);
            if (Current.equals(target)) {
                break;
            }
            Double cost = distances.get(Current);


            for (Long nextEdge : graph.nextEdges(Current)) {
                Edge edge = graph.getEdge(nextEdge);
                if (visited.contains(edge.getTailId())) {
                    continue;
                }
                Double NewDistance = cost + edge.getWight();//NextNode.getValue();
                if (NewDistance < distances.getOrDefault(edge.getTailId() , 1e9)) {
                    distances.put(edge.getTailId(), NewDistance);
                    parents.put(edge.getTailId(), Current);
                    Queue.add(edge.getTailId());
                }
            }
        }
        RoutingPath path = new RoutingPath();

//        System.out.println(sorces);
//        System.out.println(target);
//        System.out.println(distances.size());
        path.setDistance(distances.get(target));
        Long id=target;
        Node targetNode = graph.getNode(id);
        path.addPathNode(new PathNode(id , targetNode.getLat() , targetNode.getLon()  , 0));
        while (!id.equals(sorces)) {
            id=parents.get(id);
            path.addPathNode(new PathNode(id , graph.getNode(id).getLat() , graph.getNode(id).getLon()  , 0));
        }
        Collections.reverse(path.getPathNodes());
        int count = 0;
        for (PathNode pathNode : path.getPathNodes()) {
            pathNode.setOrder(count++);
        }


        return path;
    }}
