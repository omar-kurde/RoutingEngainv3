package org.example.Services;

import org.example.Graph.Element.Place;
import org.example.Graph.Element.PlaceCategory;
import org.example.PlacesService.H3.H3PlaceService;
import org.example.PlacesService.H3.H3PlaceWrapper;

import java.util.ArrayList;
import java.util.List;

public class PlaceService {
    private H3PlaceService h3PlaceService;
    public PlaceService(int RESOLUTION , int K) throws Exception {
        this.h3PlaceService = new H3PlaceService(RESOLUTION , K);
    }

    public void addPlace(Place place) {
        h3PlaceService.addPlace(place);
    }
    public void addPlaces(List<Place> places) {
        h3PlaceService.addPlaces(places);

    }

    public List<H3PlaceWrapper> getNearby(double lat, double lon , PlaceCategory category) {
        return h3PlaceService.getNearby(lat , lon , category);
    }

}
