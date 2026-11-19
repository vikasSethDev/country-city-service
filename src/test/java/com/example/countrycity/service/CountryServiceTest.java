package com.example.countrycity.service;


import com.example.countrycity.model.Country;
import com.example.countrycity.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @Test
    void shouldReturnAllCountries() {
        List<Country> countries = List.of(
                new Country(1L, "India"),
                new Country(2L, "United States")
        );
        when(countryRepository.findAll()).thenReturn(countries);

        List<Country> result = countryService.getAllCountries();

        assertEquals(2, result.size());
        assertEquals("India", result.get(0).getName());
        verify(countryRepository).findAll();
    }


}
