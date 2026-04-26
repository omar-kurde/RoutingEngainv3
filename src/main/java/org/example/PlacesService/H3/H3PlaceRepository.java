package org.example.PlacesService.H3;

import org.example.Graph.Element.PlaceCategory;

import java.util.*;

class H3PlaceRepository {
    private final Map<Long, Map<PlaceCategory, List<H3PlaceWrapper>>> h3CategoryMap = new HashMap<>();

    public void save(H3PlaceWrapper place) {
        h3CategoryMap.computeIfAbsent(place.getH3Index(), k -> new HashMap<>())
                .computeIfAbsent(place.getCategory(), k-> new ArrayList<>()).add(place);
    }

    Map<Long, Map<PlaceCategory, List<H3PlaceWrapper>>> getH3CategoryMap() {
        return h3CategoryMap;
    }

    public Map<PlaceCategory , List<H3PlaceWrapper>> findByH3(Long h3Index) {
        return h3CategoryMap.getOrDefault(h3Index, Collections.emptyMap());
    }
    public List<H3PlaceWrapper> findByH3AndCategory(Long h3Index , PlaceCategory category) {
//        System.out.println(h3CategoryMap.getOrDefault(h3Index, Collections.emptyMap()));
//        System.out.println(h3CategoryMap.getOrDefault(h3Index, Collections.emptyMap()).getOrDefault(category , new ArrayList<>()));
        return h3CategoryMap.getOrDefault(h3Index, Collections.emptyMap()).getOrDefault(category , new ArrayList<>());
    }
}
