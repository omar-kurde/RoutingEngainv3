package org.example.Graph.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Way {
    private  Long id;
    private final List<Long> nodes = new ArrayList<>();
//    private final List<Long> myEdges = new ArrayList<>();
    private final Map<String,String> tags;
    private final boolean oneWay;
    private final boolean isHighWay;

    private Way(Builder builder) {
        this.id = builder.id;
        this.tags = builder.tags;
        this.oneWay = builder.oneWay;
        this.isHighWay = builder.isHighWay;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static class Builder {
        private Long id;
        private Map<String,String> tags;
        private boolean oneWay = false;
        private boolean isHighWay = false;

        public Builder id(Long id) {
            this.id=id;
            return this;
        }
        public Builder tags(Map<String,String> tags) {
            this.tags=tags;
            return this;
        }

        public Builder oneWay(boolean oneWay) {
            this.oneWay=oneWay;
            return this;
        }
        public Builder isHighWay(boolean isHighWay) {
            this.isHighWay=isHighWay;
            return this;
        }
        public Way build() {
            return new Way(this);
        }

    }

    public Long getID(){
        return id;
    }

    public boolean containsTag(String key){
        return tags.containsKey(key);
    }

    public String getTag(String key){
        if(tags.containsKey(key)){
            return tags.get(key);
        }
        return null;
    }
    public Long numberOFNodes(){
        return (long) nodes.size();
    }


    public boolean isOneWay(){
        return oneWay;
    }
    public boolean isHighWay(){
        return isHighWay;
    }

    public void addNode(Long id){
        nodes.add(id);
    }

//    public void AddEdge(Long id){
//        myEdges.add(id);
//    }

    public Long getNode(int idx){
        return nodes.get(idx);
    }
//    public Long getEdge(int idx){
//        return myEdges.get(idx);
//    }
    public List<Long> getNodes(){
        return nodes;
    }
//    public List<Long> getEdges(){
//        return myEdges;
//    }

}
