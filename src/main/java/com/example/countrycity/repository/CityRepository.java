package com.example.countrycity.repository;


import com.example.countrycity.model.City;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CityRepository {
    private final List<City> cities = List.of(

            new City(101L, "Varanasi", 1L,
                    1200000L, "Spiritual city of India"),

            new City(102L, "Delhi", 1L,
                    32000000L, "Capital of India"),

            new City(103L, "Mumbai", 1L,
                    21000000L, "Financial capital of India"),

            new City(104L, "New York", 2L,
                    8000000L, "Major city of the USA"),

            new City(105L, "Los Angeles", 2L,
                    3800000L, "City known for Hollywood"),

            new City(106L, "Chicago", 2L,
                    2700000L, "Major city in Illinois"),

            new City(107L, "Tokyo", 3L,
                    14000000L, "Capital of Japan"),

            new City(108L, "Osaka", 3L,
                    2700000L, "Major commercial city"),

            new City(109L, "Kyoto", 3L,
                    1400000L, "Historic city of Japan")

    );

    public List<City> findAll() {
        return cities;
    }

    public Optional<City> findById(Long id) {

        return cities.stream()
                .filter(city -> city.getId().equals(id))
                .findFirst();
    }

    public List<City> findByCountryId(Long countryId) {

        return cities.stream()
                .filter(city -> city.getCountryId().equals(countryId))
                .toList();
    }


}
