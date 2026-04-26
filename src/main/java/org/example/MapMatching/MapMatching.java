package org.example.MapMatching;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;

import java.util.regex.MatchResult;

public interface MapMatching {

    Node nearestNode(Point point);
    Edge nearestEdge(Point point);

}
