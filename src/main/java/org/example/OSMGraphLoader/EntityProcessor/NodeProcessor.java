package org.example.OSMGraphLoader.EntityProcessor;

import de.topobyte.osm4j.core.model.iface.OsmNode;
import de.topobyte.osm4j.core.model.util.OsmModelUtil;
import org.example.Graph.Element.Node;
import org.example.Graph.Graph.Graph;

import java.util.concurrent.atomic.AtomicInteger;

public class NodeProcessor {
    public Node process(OsmNode node , Graph graph , AtomicInteger counter) {
        Node newNode = new Node.Builder()
                .id(counter.getAndIncrement())
                .lat(node.getLatitude())
                .lon(node.getLongitude())
                .tags(OsmModelUtil.getTagsAsMap(node))
                .build();
        graph.addNode(newNode);
        return newNode;
    }
}
