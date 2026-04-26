package org.example.Graph.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

    private  Long id;
    private final Point point;
    private final List<Long> inEdges = new ArrayList<>();
    private final List<Long> outEdges = new ArrayList<>();
    private final List<Long> myWays = new ArrayList<>();
    private final Map<String,String> tags;
    private boolean onWay = false;


    private Node(Builder builder){
        this.id = builder.id;
//        this.Lat = builder.Lat;
//        this.Lon = builder.Lon;
        this.point = new Point(builder.Lat , builder.Lon);
        this.tags = builder.tags;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public static class Builder {
        private Long id;
        private Double Lat;
        private Double Lon;
        private Map<String,String> tags;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder lat(Double lat) {
            Lat = lat;
            return this;
        }
        public Builder lon(Double lon) {
            Lon = lon;
            return this;
        }
        public Builder tags(Map<String,String> tags) {
            this.tags = tags;
            return this;
        }
        public Node build() {
            return new Node(this);
        }

    }


    public Double getLat(){
        return point.GetLat();
    }

    public Double getLon(){
        return point.GetLon();
    }

    public Point getPoint(){
        return point;
    }

    public void addWay(Long id){
        myWays.add(id);
    }

    public List<Long> getInEdges(){
        return inEdges;
    }

    public List<Long> getOutEdges(){
        return outEdges;
    }

    public List<Long> getWays(){
        return myWays;
    }

    public String getTag(String key){
        if(tags.containsKey(key)){
            return tags.get(key);
        }
        return null;
    }

    public boolean isOnWay(){
        return onWay;
    }

    public void setOnWay(boolean onway){
        this.onWay |= onway;
    }

}
