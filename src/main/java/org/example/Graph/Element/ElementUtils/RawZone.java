package org.example.Graph.Element.ElementUtils;

import org.example.Graph.Element.Point;
import org.example.Graph.Element.Way;

import java.util.List;

public class RawZone {
    private List<Integer> waysId;
    private Integer centerNodeId;
    private String name;
    private List<Integer> subRelationsId;
    public RawZone(List<Integer> ways, String name, int center , List<Integer> subRelations) {
        this.waysId = ways;
        this.centerNodeId = center;
        this.name = name;
        this.subRelationsId = subRelations;
    }


    public List<Integer> getSubRelationsId() {
        return subRelationsId;
    }

    public List<Integer> getWaysId() {
        return waysId;
    }

    public Integer getCenterNodeId() {
        return centerNodeId;
    }

    public String getName() {
        return name;
    }
}
