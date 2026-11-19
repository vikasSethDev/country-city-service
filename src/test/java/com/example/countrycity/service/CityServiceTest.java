package com.example.countrycity.service;


import com.example.countrycity.dto.PaginationResponse;
import com.example.countrycity.model.City;
import com.example.countrycity.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    void shouldReturnCityWhenIdExists() {
        City city = new City(101L, "Varanasi", 1L,
                1200000L, "Spiritual city of India");
        when(cityRepository.findById(101L)).thenReturn(java.util.Optional.of(city));

        City result = cityService.getCityById(101L);

        assertEquals("Varanasi", result.getName());
        assertEquals(1L, result.getCountryId());
    }

    @Test
    void shouldThrowExceptionWhenCityDoesNotExist() {
        when(cityRepository.findById(999L)).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class,
                () -> cityService.getCityById(999L));
    }

    @Test
    void shouldReturnCitiesWithCorrectPagination() {
        List<City> cities = List.of(
                new City(101L, "Varanasi", 1L, 1200000L, "Spiritual city"),
                new City(102L, "Delhi", 1L, 32000000L, "Capital"),
                new City(103L, "Mumbai", 1L, 21000000L, "Financial capital")
        );
        when(cityRepository.findByCountryId(1L)).thenReturn(cities);

        PaginationResponse<City> result =
                cityService.getCitiesByCountry(1L, 0, 2);

        assertEquals(2, result.getContent().size());
        assertEquals(0, result.getPage());
        assertEquals(2, result.getSize());
        assertEquals(3, result.getTotalElements());
        assertEquals(2, result.getTotalPages());
    }



    @Test
    void shouldReturnEmptyContentWhenPageIsOutOfRange() {
        when(cityRepository.findByCountryId(1L)).thenReturn(
                List.of(new City(101L, "Varanasi", 1L, 1200000L, "Spiritual city"))
        );

        PaginationResponse<City> result =
                cityService.getCitiesByCountry(1L, 5, 10);

        assertTrue(result.getContent().isEmpty());
        assertEquals(1, result.getTotalElements());
    }


    @Test
    void shouldRejectNegativePageAndInvalidSize() {
        assertThrows(ResponseStatusException.class,
                () -> cityService.getCitiesByCountry(1L, -1, 10));

        assertThrows(ResponseStatusException.class,
                () -> cityService.getCitiesByCountry(1L, 0, 0));
    }
}
