package com.example.countrycity.controller;


import com.example.countrycity.dto.PaginationResponse;
import com.example.countrycity.model.City;
import com.example.countrycity.model.Country;
import com.example.countrycity.service.CityService;

import com.example.countrycity.service.CountryService;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CountryControllerTest {

    private MockMvc mockMvc;
    private CountryService countryService;
    private CityService cityService;

    @BeforeEach
    void setUp() {
        countryService = mock(CountryService.class);
        cityService = mock(CityService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(
                new CountryController(countryService, cityService)
        ).build();
    }


    @Test
    void shouldGetCountries() throws Exception {
        when(countryService.getAllCountries()).thenReturn(
                List.of(new Country(1L, "India"))
        );

        mockMvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("India"));
    }

    @Test
    void shouldGetCitiesForCountryWithPagination() throws Exception {
        City city = new City(101L, "Varanasi", 1L,
                1200000L, "Spiritual city of India");
        when(cityService.getCitiesByCountry(1L, 0, 10)).thenReturn(
                new PaginationResponse<>(List.of(city), 0, 10, 1, 1)
        );

        mockMvc.perform(get("/countries/1/cities")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Varanasi"))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1));

        verify(cityService).getCitiesByCountry(1L, 0, 10);
    }
}
