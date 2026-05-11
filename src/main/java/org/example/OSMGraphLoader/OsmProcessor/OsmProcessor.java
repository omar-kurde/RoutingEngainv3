package org.example.OSMGraphLoader.OsmProcessor;


import de.topobyte.osm4j.core.access.OsmIterator;
import de.topobyte.osm4j.core.model.iface.EntityContainer;
import de.topobyte.osm4j.core.model.iface.EntityType;
import de.topobyte.osm4j.core.model.iface.OsmNode;
import de.topobyte.osm4j.core.model.iface.OsmWay;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Way;
import org.example.Graph.Graph.Graph;
import org.example.OSMGraphLoader.EntityProcessor.NodeProcessor;
import org.example.OSMGraphLoader.EntityProcessor.PlacesProcessor;
import org.example.OSMGraphLoader.EntityProcessor.WayProcessor;

import java.util.HashMap;
import java.util.Map;

public class OsmProcessor {
    private final Graph graph;
    private final OsmIterator osmIterator;


    private  Map<Long, Integer> offSet;

    NodeProcessor nodeProcessor = new NodeProcessor();
    WayProcessor wayProcessor = new WayProcessor();
    PlacesProcessor placesProcessor = new PlacesProcessor();

    public OsmProcessor(OsmIterator osmIterator , Graph graph) {
        offSet = new HashMap<>();
        this.osmIterator = osmIterator;
        this.graph = graph;
    }

    public void process(){

        for (EntityContainer container : osmIterator) {
            if (container.getType() == EntityType.Node){
                OsmNode node = (OsmNode) container.getEntity();
                Node newNode = nodeProcessor.process(node ,graph, graph.getNodeCount());
                offSet.put(node.getId(), newNode.getId());
                placesProcessor.process(newNode , graph);

            }
            else if (container.getType() == EntityType.Way){
                OsmWay way = (OsmWay) container.getEntity();
                Way newWay = wayProcessor.process(way , graph , graph.getWayCount() , offSet);
                placesProcessor.process(newWay , graph);
            }
        }
        offSet = null;
        System.out.println("Number of nodes: " + graph.getNodeCount().get());
    }
}
