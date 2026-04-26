package org.example.MapMatching.RTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RtreeBuilder {
    private final int min;
    private final int max;
    public RtreeBuilder(int min , int max) {
        this.min = min ;
        this.max = max;
    }

    public RNode build(List<? extends SpatialObject> items) {
        return buildLevel(new ArrayList<>(items));
    }
    private RNode buildLevel(List<? extends SpatialObject> items ) {

        if (items.size() <= max) {
            return createLeaf(items);
        }

        int S = (int) Math.ceil(Math.sqrt(items.size() / (double) max));

        items.sort(Comparator.comparingDouble(SpatialObject::latCenter));

        int sliceSize = (int) Math.ceil(items.size() / (double) S);

        List<RNode> parents = new ArrayList<>();

        for (int i = 0; i < items.size(); i += sliceSize) {

            List<SpatialObject> slice =
                    new ArrayList<>(items.subList(i, Math.min(i + sliceSize, items.size())));

            slice.sort(Comparator.comparingDouble(SpatialObject::lonCenter));

            for (int j = 0; j < slice.size(); j += max) {

                List<SpatialObject> group =
                        slice.subList(j, Math.min(j + max, slice.size()));

                parents.add(createLeaf(group));
            }
        }

        return buildUpper(parents);
    }

    private RNode buildUpper(List<RNode> nodes) {

        if (nodes.size() <= max) {
            return createInternal(nodes);
        }

        int S = (int) Math.ceil(Math.sqrt(nodes.size() / (double) max));

        nodes.sort(Comparator.comparingDouble(RNode::latCenter));

        int sliceSize = (int) Math.ceil(nodes.size() / (double) S);

        List<RNode> parents = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i += sliceSize) {

            List<RNode> slice =
                    new ArrayList<>(nodes.subList(i, Math.min(i + sliceSize, nodes.size())));

            slice.sort(Comparator.comparingDouble(RNode::lonCenter));

            for (int j = 0; j < slice.size(); j += max) {

                List<RNode> group =
                        slice.subList(j, Math.min(j + max, slice.size()));

                parents.add(createInternal(group));
            }
        }

        return buildUpper(parents);
    }

    private RNode createLeaf(List<? extends SpatialObject> items) {
        leaf node = new leaf(min , max, null);
        node.UpdateChildren(items);
        return node;
    }

    private RNode createInternal(List<RNode> children) {
        branch node = new branch(min, max , null);
        node.UpdateChildren(children);
        return  node;
    }
}
