package com.example.countrycity.service;


import com.example.countrycity.dto.PaginationResponse;
import com.example.countrycity.model.City;
import com.example.countrycity.repository.CityRepository;
import com.example.countrycity.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;


    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(()->
                        new RuntimeException("City not found")
                );
    }



    public PaginationResponse<City> getCitiesByCountry(Long countryId, int page, int size) {

        if (page < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "page must be >= 0");
        }
        if (size < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "size must be >= 1");
        }

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

        return new PaginationResponse<>(content, page, size, totalElements, totalPages);
    }





}
