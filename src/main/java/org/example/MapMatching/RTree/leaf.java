package org.example.MapMatching.RTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.example.util.Math.Projection.closestPointOnLine;


public class leaf implements RNode{
    private final int max;
    private final int min;
    private int size;
    private final boolean isLeaf;
    private RNode parent;
    Rectangle rect;
    private List<SpatialObject> children;
    public leaf(int min, int max, RNode parent) {
        this.size=0;
        this.isLeaf = true;
        this.max =max;
        this.min =min;
        this.parent=parent;
        this.children=new ArrayList<>();
        this.rect = new Rectangle();
    }


    public void UpdateChildren(List<? extends SpatialObject> ch){
        children.clear();
        rect = new Rectangle();
        this.size = 0;
        for (SpatialObject child : ch) {
            Add_object(child);
        }

    }

    @Override
    public boolean isLeaf() {
        return isLeaf;
    }

    public int getSize() {
        return size;
    }

    public void Add_object(SpatialObject obj) {
        this.children.add(obj);

        UpdateSize(1);
        rect.AddRect(obj.getMBR());
    }

    public RNode insert(SpatialObject obj) {
        Add_object(obj);
        if (this.size > max) {
            return split();
        }
        return null;
    }

    private RNode split(){

        List<SpatialObject> cur = new ArrayList<>();
        List<SpatialObject> neww = new ArrayList<>();

        SpatialObject seed1 =null,seed2=null;
        double worst=-1;
        List<SpatialObject> temp = new ArrayList<>(this.children);

        for (int i =0;i<temp.size() ; i++){
            for (int j=i+1;j<temp.size();j++){
                Rectangle rect1 =  temp.get(i).getMBR();
                Rectangle rect2 = temp.get(j).getMBR();
                Rectangle comp = Rectangle.Union(rect1,rect2);
                double cost = comp.area() - rect1.area() - rect2.area();
                if (cost > worst){
                    worst=cost;
                    seed1  = temp.get(i);
                    seed2 = temp.get(j);
                }
            }
        }
        cur.add(seed1);
        neww.add(seed2);
        temp.remove(seed1);
        temp.remove(seed2);

        for (SpatialObject obj : temp){
            double enalr1 = Rectangle.enlargement(cur , obj);
            double enalr2 = Rectangle.enlargement(neww , obj);
            if (enalr1 < enalr2){
                cur.add(obj);
            }
            else
                neww.add(obj);
        }
        UpdateChildren(cur);
        leaf newNode = new leaf(min, max,this.getParent());
        newNode.UpdateChildren(neww);
        return newNode;


    }
    @Override
    public SpatialObject search(RPoint point,DoubleRef dis){
        return search(point , dis,0D);

    }
    @Override
    public SpatialObject search(RPoint point , DoubleRef dis,double d) {
        List<SpatialObject> temp = new ArrayList<>(this.children);
        temp.sort(Comparator.comparingDouble(o -> o.getMBR().distance(point)));

        SpatialObject best = null;

        for (SpatialObject child : temp){
            if (child.getMBR().distance(point) > dis.value )
                continue;
            double cost = (point).distance(closestPointOnLine(((REdge)child).getStart(),((REdge)child).getEnd(), point));

            if (cost<=d)continue;
            if (cost<dis.value){
                dis.value = cost;
                best = child;
            }
            System.out.println(best);
        }
        return best;
    }


    @Override
    public RNode  getParent() {
        return this.parent;
    }
    public void SetParent(RNode parent){
        this.parent=parent;
    }
    public List<SpatialObject> getChildren(){
        return this.children;
    }

    private void UpdateSize(int value){
        size+=value;
    }
    public Rectangle getMBR(){
        return this.rect;
    }

    @Override
    public RPoint center() {
        return rect.center();
    }

    @Override
    public double latCenter() {
        return rect.latCenter();
    }

    @Override
    public double lonCenter() {
        return rect.lonCenter();
    }

}
