package org.example.Graph.Element;

public class Edge {
    private  Long id;
    private final Long wayId;
    private final Long headId;
    private final Long tailId;
    private final boolean isHighWay;
    private final boolean oneWay ;
    private final Double weight;


    private Edge(Builder builder){
        this.id = builder.id;
        this.wayId = builder.wayId;
        this.headId = builder.headId;
        this.tailId = builder.tailId;
        this.weight = builder.weight;
        this.isHighWay = builder.isHighWay;
        this.oneWay = builder.oneWay;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static class  Builder{
        private Long id;
        private Long wayId;
        private Long headId;
        private Long tailId;
        private boolean oneWay= false ;
        private boolean isHighWay = false ;

        Double weight;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder wayId(Long wayId) {
            this.wayId = wayId;
            return this;
        }
        public Builder HeadId(Long startId) {
            this.headId = startId;
            return this;
        }
        public Builder tailId(Long endId) {
            this.tailId = endId;
            return this;
        }
        public Builder weight(Double weight) {
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

    public Long getWayId(){
        return this.wayId;
    }
    public Double getWight()
    {
//        if (this.weight==null)
        return this.weight;
    }
    public Long getId(){
        return this.id;
    }
    public Long getHeadId(){
        return this.headId;
    }

    public Long getTailId(){
        return this.tailId;
    }
    public boolean isOneWay(){
        return this.oneWay;
    }
    public boolean IsHighWay(){
        return this.isHighWay;
    }
}
