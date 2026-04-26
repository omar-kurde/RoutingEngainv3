package org.example.PlacesService.H3;

import org.example.Graph.Element.Place;
import org.example.Graph.Element.PlaceCategory;

import java.util.*;

public class H3PlaceService {
    private final H3Service h3Service;
    private final H3PlaceRepository repo;
    private final int RESOLUTION;
    private final int K;
    public H3PlaceService(int RESOLUTION , int k) throws Exception {
        this.RESOLUTION = RESOLUTION;
        this.K = k;
        this.h3Service = new H3Service();
        this.repo = new H3PlaceRepository();
    }

    public void addPlace(Place place) {
        Long h3Index = h3Service.getIndex(place.getCenter().getLat(), place.getCenter().getLon() , RESOLUTION);
//        System.out.println(h3Index);
        repo.save(new H3PlaceWrapper(h3Index, place));
    }
    public void addPlaces(List<Place> places) {
        for (Place place : places) {
            if (place==null)
                System.out.println("ajsda");
            addPlace(place);
        }

    }

    public List<H3PlaceWrapper> getNearby(double lat, double lon , PlaceCategory category) {
//        System.out.println(repo.getH3CategoryMap());
        Long center = h3Service.getIndex(lat, lon, RESOLUTION);


//        System.out.println(center);

        List<Long> neighbors = h3Service.getNeighbors(center , K);
//        System.out.println(neighbors);
        List<H3PlaceWrapper> result = new ArrayList<>();

        for (Long cell : neighbors) {
            result.addAll(repo.findByH3AndCategory(cell , category));
        }

        return result;
    }

}
