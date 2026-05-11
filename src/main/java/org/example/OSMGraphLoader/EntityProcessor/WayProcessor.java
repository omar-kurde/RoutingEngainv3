package org.example.OSMGraphLoader.EntityProcessor;

import com.slimjars.dist.gnu.trove.list.TLongList;
import com.slimjars.dist.gnu.trove.list.array.TLongArrayList;
import de.topobyte.osm4j.core.model.iface.OsmWay;
import de.topobyte.osm4j.core.model.util.OsmModelUtil;
import org.example.Graph.Element.Way;
import org.example.Graph.Graph.Graph;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WayProcessor {
    public Way process(OsmWay way , Graph graph , AtomicInteger counter , Map<Long,Integer> offSet) {
        TLongList TempList   = new TLongArrayList(OsmModelUtil.nodesAsList(way));

        Map<String,String> tempTags  = OsmModelUtil.getTagsAsMap(way);
        boolean isHighway = tempTags.containsKey("highway");
        boolean isOneWay = this.IsOneWay(way);
        Way newWay = new Way.Builder()
                .id(counter.get())
                .tags(OsmModelUtil.getTagsAsMap(way))
                .isHighWay(OsmModelUtil.getTagsAsMap(way).containsKey("highway"))
                .oneWay(isOneWay)
                .build();

        for (int i = 0; i < TempList.size(); i++) {
            int id  = offSet.get(TempList.get(i));

            newWay.addNode(id);

            graph.NODES_LIST().get(id)
                        .setOnWay(isHighway);

//            graph.NODES_LIST().get((int)id)
//                    .addWay(counter.get());
        }
        counter.incrementAndGet();
        graph.addWay(newWay);
        return newWay;

    }



    boolean IsOneWay(OsmWay way) {
        String dir = OsmModelUtil.getTagsAsMap(way).get("highway");
        return dir != null && !dir.isEmpty() && !dir.equals("no");
    }

}
