package org.example.Graph.Element;

import it.unimi.dsi.fastutil.ints.IntArrayList;

import java.util.List;
import java.util.Map;

public class Way {
    private  int id;
    private final IntArrayList nodes = new IntArrayList();


    //    private final List<long> myEdges = new ArrayList<>();
    private Map<String,String> tags;
    private final boolean oneWay;
    private final boolean isHighWay;

    private Way(Builder builder) {
        this.id = builder.id;
        if (!builder.tags.isEmpty())
            this.tags = builder.tags;
        this.oneWay = builder.oneWay;
        this.isHighWay = builder.isHighWay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
    public static class Builder {
        private int id;
        private Map<String,String> tags;
        private boolean oneWay = false;
        private boolean isHighWay = false;

        public Builder id(int id) {
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

    public int getId(){
        return id;
    }

    public boolean containsTag(String key){
        return tags.containsKey(key);
    }

    public String getTag(String key){
        if(tags!=null && tags.containsKey(key)){
            return tags.get(key);
        }
        return null;
    }
    public int numberOFNodes(){
        return  nodes.size();
    }


    public boolean isOneWay(){
        return oneWay;
    }
    public boolean isHighWay(){
        return isHighWay;
    }

    public void addNode(int id){
        nodes.add(id);
    }

//    public void AddEdge(int id){
//        myEdges.add(id);
//    }

    public int getNode(int idx){
        return nodes.get(idx).intValue();
    }
//    public int getEdge(int idx){
//        return myEdges.get(idx);
//    }
    public IntArrayList getNodes(){
        return nodes;
    }
//    public List<long> getEdges(){
//        return myEdges;
//    }

}
