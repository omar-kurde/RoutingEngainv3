package org.example.PlacesService.H3;

import com.uber.h3core.H3Core;

import java.util.List;

class H3Service {
    private final H3Core h3;

//    private static final int RESOLUTION = 7;

    public H3Service() throws Exception {
        this.h3 = H3Core.newInstance();

    }

    public Long  getIndex(double lat, double lon , int resolution ) {
        return h3.latLngToCell(lat, lon, resolution);
    }

    public List<Long> getNeighbors(Long h3Index, int k) {
        return h3.gridDisk(h3Index, k);
    }
}
