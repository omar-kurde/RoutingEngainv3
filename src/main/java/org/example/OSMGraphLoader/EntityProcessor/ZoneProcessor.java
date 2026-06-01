package org.example.OSMGraphLoader.EntityProcessor;

import org.example.Graph.Element.ElementUtils.RawZone;
import org.example.Graph.Element.Node;
import org.example.Graph.Element.Point;
import org.example.Graph.Element.Way;
import org.example.Graph.Element.Zone;
import org.example.Graph.Graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class ZoneProcessor {

    public List<Zone> process(List<RawZone> rawZones , Graph graph) {
        List<Zone> zones = new ArrayList<>();
        for (RawZone z : rawZones) {
            zones.add(createZone(z, graph));
        }
        linkZones(zones , rawZones);
        for (Zone zone : zones) {
            graph.addZone(zone);
        }
        return zones;
    }
    private Zone createZone(RawZone rawZone , Graph graph) {
        List<Point> points = new ArrayList<>();
        Node node = graph.getNode(rawZone.getCenterNodeId());

        for (int wayId : rawZone.getWaysId()){
            Way way = graph.getWay(wayId);
            for (int nodeId : way.getNodes()){
                points.add(graph.getNode(nodeId).getPoint());
            }
        }
        Zone zone = new Zone(points , rawZone.getName() , node.getPoint());
        return zone;
    }

    private void linkZones(List<Zone> zones, List<RawZone> rawZones) {
        for (int i = 0; i < rawZones.size(); i++) {
            for (int subRelationId : rawZones.get(i).getSubRelationsId()){
                zones.get(i).getSubZones().add(zones.get(subRelationId));
                zones.get(subRelationId).setParentId(i);
            }
        }

    }
}
