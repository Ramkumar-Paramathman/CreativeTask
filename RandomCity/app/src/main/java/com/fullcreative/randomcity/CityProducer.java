package com.example.randomcityapp;

import java.util.List;
import java.util.Random;

public class CityProducer {
    private final List<String> cities;
    private final List<String> colors;
    private final Random random = new Random();

    public CityProducer(List<String> cities, List<String> colors) {
        this.cities = cities;
        this.colors = colors;
    }

    public String getRandomCity() {
        return cities.get(random.nextInt(cities.size()));
    }

    public String getRandomColor() {
        return colors.get(random.nextInt(colors.size()));
    }
}
