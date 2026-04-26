package org.example.MapMatching;

import org.example.Graph.Element.Edge;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;
import org.example.Graph.Graph.Graph;

public interface NearestEdgeFinder {

    Edge nearestEdge(Point point);

}
