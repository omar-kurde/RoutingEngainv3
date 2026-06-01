package org.example.OSMGraphLoader.OsmProcessor;


import de.topobyte.osm4j.core.access.OsmIterator;
import de.topobyte.osm4j.core.model.iface.*;
import org.example.Graph.Element.ElementUtils.AllowedZones;
import org.example.Graph.Element.ElementUtils.RawZone;
import org.example.Graph.Element.ElementUtils.RelationOffSet;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Way;
import org.example.Graph.Graph.Graph;
import org.example.OSMGraphLoader.EntityProcessor.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OsmProcessor {
    private final Graph graph;
    private final OsmIterator osmIterator;


    private  Map<Long, Integer> nodeOffSet;
    private  Map<Long, Integer> wayOffSet;
    private RelationOffSet relationOffSet;
    NodeProcessor nodeProcessor = new NodeProcessor();
    WayProcessor wayProcessor = new WayProcessor();
    PlacesProcessor placesProcessor = new PlacesProcessor();
    RawZoneProcessor rawZoneProcessor = new RawZoneProcessor();
    ZoneProcessor zoneProcessor = new ZoneProcessor();
    List<RawZone> rawZones = new ArrayList<RawZone>();
    public OsmProcessor(OsmIterator osmIterator , Graph graph) {
        nodeOffSet = new HashMap<>();
        wayOffSet = new HashMap<>();
        relationOffSet = new RelationOffSet();
        this.osmIterator = osmIterator;
        this.graph = graph;
    }

    public void process(){

        for (EntityContainer container : osmIterator) {
            if (container.getType() == EntityType.Node){
                OsmNode node = (OsmNode) container.getEntity();
                Node newNode = nodeProcessor.process(node ,graph, graph.getNodeCount());
                nodeOffSet.put(node.getId(), newNode.getId());
                if (newNode.getId() == 1794269)
                    System.out.println("---------->1 "+node.getId());
                if (newNode.getId() == 2928692)
                    System.out.println("---------->2 "+node.getId());

                placesProcessor.process(newNode , graph);

            }
            else if (container.getType() == EntityType.Way){
                OsmWay way = (OsmWay) container.getEntity();
                Way newWay = wayProcessor.process(way , graph , graph.getWayCount() , nodeOffSet);
                wayOffSet.put(way.getId() , newWay.getId());
                placesProcessor.process(newWay , graph);
            }
            else if (container.getType() == EntityType.Relation){

                OsmRelation relation = (OsmRelation) container.getEntity();
                if (!AllowedZones.contains(relation.getId()))
                    continue;
                RawZone rawZone = rawZoneProcessor.process(relation , relationOffSet , wayOffSet , nodeOffSet);
                rawZones.add(rawZone);
            }
        }
        zoneProcessor.process(rawZones , graph);
        nodeOffSet = null;
    }
}
