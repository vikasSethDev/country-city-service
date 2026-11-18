package com.example.countrycity.repository;


import com.example.countrycity.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryRepository {

    private final List<Country> countries  = List.of(
            new Country(1L, "India"),
            new Country(2L, "United States"),
            new Country(3L, "Japan"),
            new Country(4L, "United Kingdom"),
            new Country(5L, "Australia")
    );

    public List<Country> findAll(){
        return countries;
    }

    public Optional<Country> findById(Long id){
        return countries.stream()
                .filter(country -> country.getId().equals(id)).findFirst();
    }

}
