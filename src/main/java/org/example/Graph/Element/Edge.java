package org.example.Graph.Element;

public class Edge {
    private  int id;
    private final int wayId;
    private final int headId;
    private final int tailId;
    private final boolean isHighWay;
    private final boolean oneWay ;
    private final double weight;


    private Edge(Builder builder){
        this.id = builder.id;
        this.wayId = builder.wayId;
        this.headId = builder.headId;
        this.tailId = builder.tailId;
        this.weight = builder.weight;
        this.isHighWay = builder.isHighWay;
        this.oneWay = builder.oneWay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class  Builder{
        private int id;
        private int wayId;
        private int headId;
        private int tailId;
        private boolean oneWay= false ;
        private boolean isHighWay = false ;

        double weight;

        public Builder id(int id) {
            this.id = id;
            return this;
        }
        public Builder wayId(int wayId) {
            this.wayId = wayId;
            return this;
        }
        public Builder HeadId(int startId) {
            this.headId = startId;
            return this;
        }
        public Builder tailId(int endId) {
            this.tailId = endId;
            return this;
        }
        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }
        public Builder oneWay(boolean oneWay) {
            this.oneWay = oneWay;
            return this;
        }
        public Builder isHighWay(boolean isHighWay) {
            this.isHighWay = isHighWay;
            return this;
        }
        public Edge build() {
            return new Edge(this);
        }

    }

    public int getWayId(){
        return this.wayId;
    }
    public double getWight()
    {
//        if (this.weight==null)
        return this.weight;
    }
    public int getId(){
        return this.id;
    }
    public int getHeadId(){
        return this.headId;
    }

    public int getTailId(){
        return this.tailId;
    }
    public boolean isOneWay(){
        return this.oneWay;
    }
    public boolean IsHighWay(){
        return this.isHighWay;
    }
}
