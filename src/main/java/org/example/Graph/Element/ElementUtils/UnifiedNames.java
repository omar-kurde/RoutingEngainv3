package org.example.Graph.Element.ElementUtils;

import org.example.Graph.Element.PlaceCategory;

import java.util.HashMap;
import java.util.Map;

public class UnifiedNames {
//    RESTAURANT,
//    FAST_FOOD,
//    FOOD_COURT,
//    PARK,
//    MOSQUE,
//    PARKING,
//    PARKING_ENTRANCE,
//    CONVENIENCE,//SHOP
//    FOOD,//SHOP
//    FUEL,
//    PHARMACY,
//    CHEMIST, //SHOP
//    SUPERMARKET, // AND SHOP
//    MARKETPLACE

    private static final Map<String , PlaceCategory> places = new HashMap<>();

    static {
        places.put("RESTAURANT" , PlaceCategory.RESTAURANT);
        places.put("FAST_FOOD" , PlaceCategory.RESTAURANT);
        places.put("FOOD_COURT" , PlaceCategory.RESTAURANT);

        places.put("PARK" , PlaceCategory.PARK);

        places.put("MOSQUE" , PlaceCategory.MOSQUE);

        places.put("PARKING" , PlaceCategory.PARKING);
        places.put("PARKING_ENTRANCE" , PlaceCategory.PARKING);

        places.put("CONVENIENCE" , PlaceCategory.SUPERMARKET);
        places.put("FOOD" , PlaceCategory.SUPERMARKET);
        places.put("SUPERMARKET" , PlaceCategory.SUPERMARKET);
        places.put("MARKETPLACE" , PlaceCategory.SUPERMARKET);

        places.put("FUEL" , PlaceCategory.FUEL);

        places.put("PHARMACY" , PlaceCategory.PHARMACY);
        places.put("CHEMIST" , PlaceCategory.PHARMACY);
//        mp.put("FOOD" , PlaceCategory.RESTAURANT);
    }

    public static boolean contain(String key){
        return places.containsKey(key.toUpperCase());
    }
    public static PlaceCategory get(String key){
        return places.get(key.toUpperCase());
    }

}
