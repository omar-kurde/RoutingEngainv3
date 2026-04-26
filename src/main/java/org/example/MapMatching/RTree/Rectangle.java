package org.example.MapMatching.RTree;

import java.util.List;

public class Rectangle {
    double minX , minY, maxX, maxY;

    Rectangle(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    Rectangle(){
        this.minX = Double.MAX_VALUE;
        this.minY = Double.MAX_VALUE;
        this.maxX = Double.MIN_VALUE;
        this.maxY = Double.MIN_VALUE;
    }
    Rectangle(RPoint p1 , RPoint p2 ){
        this.minX = Math.min(p1.getLat() , p2.getLat());
        this.maxX = Math.max(p1.getLat() , p2.getLat());
        this.minY = Math.min(p1.getLon() , p2.getLon());
        this.maxY = Math.max(p1.getLon() , p2.getLon());

    }
    public void AddPoint(RPoint point){
        this.minX = Math.min(minX  ,point.getLat());
        this.minY = Math.min(minX  ,point.getLon());
        this.maxX = Math.max(maxX  ,point.getLat());
        this.maxY = Math.max(maxY  ,point.getLon());
    }
    public void AddRect(RNode node){
        this.minX = Math.min(minX  ,node.getMBR().getMinX());
        this.minY = Math.min(minY  ,node.getMBR().getMinY());
        this.maxX = Math.max(maxX  ,node.getMBR().getMaxX());
        this.maxY = Math.max(maxY  ,node.getMBR().getMaxY());
    }
    public void AddRect(Rectangle rect){
        this.minX = Math.min(minX  ,rect.getMinX());
        this.minY = Math.min(minY  ,rect.getMinY());
        this.maxX = Math.max(maxX  ,rect.getMaxX());
        this.maxY = Math.max(maxY  ,rect.getMaxY());
    }
    static Rectangle Union(Rectangle rect1 ,Rectangle rect2){
        Rectangle result = new Rectangle();
        result.AddRect(rect1);
        result.AddRect(rect2);
        return result;
    }
    static  double enlargement(Rectangle rect1 ,Rectangle rect2){
        return rect1.area() - Union(rect1,rect2).area();
    }
    static  double enlargement(List<SpatialObject> group , SpatialObject other){
        Rectangle result = new Rectangle();
        result.AddRect(other.getMBR());
        for (SpatialObject obj : group){
            result = Union(result , obj.getMBR());
        }
        return result.area();
    }
    static  double enlargement(List<RNode> group , RNode other){
        Rectangle result = new Rectangle();
        result.AddRect(other.getMBR());
        for (RNode obj : group){
            if (obj == null)continue;
            result = Union(result , obj.getMBR());
        }
        return result.area();
    }
    public double perimeter(){
        return (this.maxX - this.minX)*2  + (this.maxY - this.minY)*2;
    }
    public double area(){
        return (maxX - minX) * (maxY - minY);
    }
    public double distance(RPoint point){

        double lat1 = Math.toRadians(point.getLat());
        double lon1 = Math.toRadians(point.getLon());
        double lat2 = Math.toRadians(Math.max(getMinX(), Math.min(point.getLat(), getMaxX())));
        double lon2 = Math.toRadians(Math.max(getMinY(), Math.min(point.getLon(), getMaxY())));
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));

        return rad * c;
    }

    public RPoint center(){
        return new RPoint((minX+maxX)/2 , (minY + maxY)/2);
    }
    public double latCenter(){
        return (minX+maxX)/2;
    }
    public double lonCenter(){
        return (minY + maxY)/2;
    }
    public double getMaxX() {
        return maxX;
    }
    public double getMaxY() {
        return maxY;
    }
    public double getMinX() {
        return minX;
    }
    public double getMinY() {
        return minY;
    }
}
