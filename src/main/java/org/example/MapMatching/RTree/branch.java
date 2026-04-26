package org.example.MapMatching.RTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class branch implements RNode{
    private final int max;
    private final int min;
    private int size;
    private final boolean isLeaf;
    private RNode parent;
    private List<RNode> children;
    Rectangle rect;
    public branch(int Min, int Max, RNode parent) {
        this.size=0;
        this.isLeaf = false;
        this.max = Max;
        this.min = Min;
        this.children = new ArrayList<>();
        this.parent=parent;
        this.rect = new Rectangle();

    }
    public void UpdateChildren(List<RNode> ch){
        children.clear();
        rect = new Rectangle();
        this.size = 0;
        for (RNode child : ch) {
            Add_object(child);
        }

    }

    public void Add_object(RNode obj){
        this.children.add(obj);
        obj.SetParent(this);
        UpdateSize(1);
        rect.AddRect(obj);

    }
    private RNode chooseChild(SpatialObject obj){
        RNode best = null;
        double bestperimeter = Double.MAX_VALUE;

        for (RNode child : children){
            double temp = Rectangle.Union(child.getMBR() ,obj.getMBR()).perimeter() - child.getMBR().perimeter();
            if (temp < bestperimeter){
                best = child;
                bestperimeter = temp;
            }
        }
        return best;
    }
    public RNode insert(SpatialObject obj){
        RNode next = chooseChild(obj);
        RNode newChild =  next.insert(obj);
        rect.AddRect(next);

        if (newChild != null){
            Add_object(newChild);
        }
        if (this.size > max){
            return split();
        }
        return null;
    }
    RNode split(){
        List<RNode> cur = new ArrayList<>();
        List<RNode> neww = new ArrayList<>();
        RNode seed1 =null,seed2=null;
        double worst=-1;
        List<RNode> temp = new ArrayList<>(this.children);

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

        for (RNode obj : temp){
            double enalr1 = Rectangle.enlargement(cur , obj);
            double enalr2 = Rectangle.enlargement(neww , obj);
            if (enalr1 < enalr2){
                cur.add(obj);
            }
            else
                neww.add(obj);
        }
        UpdateChildren(cur);
        branch newNode = new branch(min, max,this.getParent());
        newNode.UpdateChildren(neww);
        return newNode;

    }

    @Override
    public SpatialObject search(RPoint point ,DoubleRef dis){
        return search(point,dis,0D);
    }

    @Override
    public SpatialObject search(RPoint point ,DoubleRef dis,Double d){
        List<RNode> temp = new ArrayList<>(this.children);
        temp.sort(Comparator.comparingDouble(o -> o.getMBR().distance(point)));
        SpatialObject best = null;

        for (RNode child : temp){
            if (child.getMBR().distance(point) > dis.value)
                continue;
            SpatialObject MayBeBest = child.search(point , dis, d);
            if (MayBeBest == null)continue;
            best = MayBeBest;
            System.out.println(best.toString());
        }
        return best;
    }

    @Override
    public boolean isLeaf() {
        return isLeaf;
    }
    public int getSize(){
        return size;
    }

    @Override
    public RNode  getParent(){
        return this.parent;
    }
    public void SetParent(RNode parent){
        this.parent=parent;
    }
    private void UpdateSize(int value){
        size+=value;
    }
    public List<RNode> getChildren(){
        return this.children;
    }
    @Override
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
