package org.example.Graph.Element;


import java.util.Map;

public class Node {

    private int id;
    private final Point point;
//    private final intArrayList myWays = new intArrayList();
    private  Map<String,String> tags;
    private boolean onWay = false;


    private Node(Builder builder){
        this.id = builder.id;
//        this.Lat = builder.Lat;
//        this.Lon = builder.Lon;
        this.point = new Point(builder.Lat , builder.Lon);
        if (builder.tags!=null && !builder.tags.isEmpty())
            this.tags = builder.tags;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public static class Builder {
        private int id;
        private double Lat;
        private double Lon;
        private Map<String,String> tags;

        public Builder id(int id) {
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


    public double getLat(){
        return point.getLat();
    }

    public double getLon(){
        return point.getLon();
    }

    public Point getPoint(){
        return point;
    }

//    public void addWay(int id){
//        myWays.add(id);
//    }


//    public intArrayList getWays(){
//        return myWays;
//    }

    public String getTag(String key){
        if(tags!=null && tags.containsKey(key)){
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
