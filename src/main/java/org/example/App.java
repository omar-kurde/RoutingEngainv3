package org.example;



public class App 
{
    public static void main( String[] args ) throws Exception {
        RoutingEngine engine = new RoutingEngine();
        engine.start();


        engine.shortestRoute(32.562859005485464,35.841388462121806,32.57001089999999,35.844161311111115);
    }
}
