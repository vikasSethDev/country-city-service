package com.example.countrycity.service;


import com.example.countrycity.dto.PaginationResponse;
import com.example.countrycity.model.City;
import com.example.countrycity.repository.CityRepository;
import com.example.countrycity.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;


    public Page<City> getCitiesByCountryId(
            Long countryId, Pageable pageable) {

        countryRepository.findById(countryId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Country not found with ID: " + countryId
                        )
                );

        List<City> cities = cityRepository.findByCountryId(countryId);

        List<City> paginatedCities = cities.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .toList();

        return new PageImpl<>(
                paginatedCities,
                pageable,
                cities.size()
        );
    }

//    public List<City> getCitiesByCountryId(Long countryId) {
//        countryRepository.findById(countryId).orElseThrow(() ->
//                new RuntimeException("Contry not found")
//        );
//
//        return cityRepository.findByCountryId(countryId);
//
//    }


    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(()->
                        new RuntimeException("City not found")
                );
    }



    public PaginationResponse<City> getCitiesByCountry(
            Long countryId, int page, int size) {

        List<City> cities = cityRepository.findByCountryId(countryId);

        int totalElements = cities.size();

        int start = page * size;
        int end = Math.min(start + size, totalElements);

        List<City> content;

        if (start >= totalElements) {
            content = List.of();
        } else {
            content = cities.subList(start, end);
        }

        int totalPages = (int) Math.ceil((double) totalElements / size);

        return new PaginationResponse<>(
                content,
                page,
                size,
                totalElements,
                totalPages
        );
    }





}
