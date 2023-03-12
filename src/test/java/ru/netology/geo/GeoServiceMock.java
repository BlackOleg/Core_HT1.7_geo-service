package ru.netology.geo;

import ru.netology.entity.Location;

public class GeoServiceMock implements GeoService{

    private Location value;
    @Override
    public Location byIp(String ip) {
        return value;
    }

    @Override
    public Location byCoordinates(double latitude, double longitude) {
        return null;
    }
    public void setValue(Location value) {
        this.value = value;
    }
}
