package com.example.countrycity.controller;

import com.example.countrycity.model.City;
import com.example.countrycity.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CityControllerTest {

    private MockMvc mockMvc;
    private CityService cityService;

    @BeforeEach
    void setUp() {
        cityService = mock(CityService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(
                new CityController(cityService)
        ).build();
    }


    @Test
    void shouldGetCityDetailsById() throws Exception {
        City city = new City(101L, "Varanasi", 1L,
                1200000L, "Spiritual city of India");
        when(cityService.getCityById(101L)).thenReturn(city);

        mockMvc.perform(get("/cities/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(101))
                .andExpect(jsonPath("$.name").value("Varanasi"))
                .andExpect(jsonPath("$.countryId").value(1));
    }
}
