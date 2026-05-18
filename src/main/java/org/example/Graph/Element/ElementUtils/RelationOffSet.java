package org.example.Graph.Element.ElementUtils;

import java.util.HashMap;
import java.util.Map;

public class RelationOffSet {
    private Map<Long ,Integer> offSet;
    private int size=0;
    public RelationOffSet() {
        this.offSet = new HashMap<>();
    }
    public Map<Long ,Integer> getOffSet() {
        return offSet;
    }
    public boolean contain(Long id){
        return offSet.containsKey(id);
    }
    public Integer getAndAdd(Long id){
        if (offSet.containsKey(id))
            return offSet.get(id);
        offSet.put(id, size);
        return size;
    }

}
