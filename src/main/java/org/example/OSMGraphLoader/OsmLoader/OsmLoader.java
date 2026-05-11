package org.example.OSMGraphLoader.OsmLoader;

import de.topobyte.osm4j.core.access.OsmIterator;
import de.topobyte.osm4j.pbf.seq.PbfIterator;
import de.topobyte.osm4j.xml.dynsax.OsmXmlIterator;
import java.io.IOException;
import java.io.InputStream;

public class OsmLoader {
    private OsmIterator osmIterator;
    private InputStream inputStream;

    public void init() throws IOException {
        try{
            LoadOsmData();
        }
        catch(IOException e){
            e.printStackTrace();
            throw e;
        }

    }

    private void LoadOsmData() throws IOException {
        try {
            String filename = "OSM/jordan-260420.osm.pbf";
//        InputStream input = new FileInputStream(filename);
            inputStream = getClass().getClassLoader().getResourceAsStream(filename);

            this.osmIterator = new PbfIterator(inputStream, true);

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    public OsmIterator getOsmIterator() {
        return osmIterator;
    }
    public void closeStream() throws IOException {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
