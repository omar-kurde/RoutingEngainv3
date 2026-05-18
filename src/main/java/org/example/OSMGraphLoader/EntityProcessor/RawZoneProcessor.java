package org.example.OSMGraphLoader.EntityProcessor;

import de.topobyte.osm4j.core.model.iface.EntityType;
import de.topobyte.osm4j.core.model.iface.OsmRelation;
import de.topobyte.osm4j.core.model.iface.OsmRelationMember;
import de.topobyte.osm4j.core.model.util.OsmModelUtil;
import org.example.Graph.Element.ElementUtils.AllowedZones;
import org.example.Graph.Element.ElementUtils.RawZone;
import org.example.Graph.Element.ElementUtils.RelationOffSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RawZoneProcessor {
    public RawZone process(OsmRelation relation , RelationOffSet relationOffSet , Map<Long , Integer> waysOfSet , Map<Long, Integer> nodeOfSet) {
        List<Integer> ways = new ArrayList<>();
        Integer centerNode = null;
        List<Integer> subRelation = new ArrayList<>();
        for (int i = 0;i < relation.getNumberOfMembers(); i++){
            OsmRelationMember member = relation.getMember(i);
            if (member.getType() == EntityType.Node){
                if (member.getRole().toLowerCase().equals("admin_centre")){
                    centerNode = nodeOfSet.get(member.getId());
                }
            }
            else if (member.getType() == EntityType.Way){
                ways.add(waysOfSet.get(member.getId()));
            }
            else if (member.getId() == AllowedZones.getMain() && member.getType() == EntityType.Relation){
                subRelation.add(relationOffSet.getAndAdd(member.getId()));
            }
        }

        RawZone rawZone = new RawZone(ways , OsmModelUtil.getTagsAsMap(relation).getOrDefault("name" , "unkown") , centerNode ,subRelation);
        return rawZone;
    }
}
