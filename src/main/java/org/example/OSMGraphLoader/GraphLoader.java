package org.example.OSMGraphLoader;

import org.example.Graph.Graph.Graph;
import org.example.Graph.Graph.NormalGraph;
import org.example.OSMGraphLoader.GraphBuilder.GraphBuilder;
import org.example.OSMGraphLoader.OsmLoader.OsmLoader;
import org.example.OSMGraphLoader.OsmProcessor.OsmProcessor;

import java.io.IOException;

public class GraphLoader {

    private final OsmLoader osmLoader = new OsmLoader();
    private  GraphBuilder graphBuilder;
    private OsmProcessor osmProcessor;
    public Graph loadGraph() throws IOException {
        try {
            osmLoader.LoadOsmData();
            osmLoader.init();
            Graph graph = new NormalGraph();
//            osmIterator = osmLoader.getOsmIterator();
            osmProcessor = new OsmProcessor(osmLoader.getOsmIterator(), graph);
            osmProcessor.process();
            osmLoader.closeStream();

            graphBuilder = new GraphBuilder(graph);
            graphBuilder.buildGraph();
            System.out.println(graph.NODES_LIST().size());
            return graph;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IOException();
        }
    }
}
