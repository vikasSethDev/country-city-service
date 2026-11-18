package com.example.countrycity.controller;


import com.example.countrycity.dto.PaginationResponse;
import com.example.countrycity.model.City;
import com.example.countrycity.model.Country;
import com.example.countrycity.service.CityService;
import com.example.countrycity.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@Tag(name="country", description = "Country API")
public class CountryController {
    private final CountryService countryService;
    private final CityService cityService;


    public CountryController(
            CountryService countryService,
            CityService cityService) {

        this.countryService = countryService;
        this.cityService = cityService;
    }

    @Operation(summary = "Get all countries", description = "Returns the list of all countries")
    @ApiResponse(responseCode = "200", description = "Cuntry Fetch Successfully")
    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @Operation(summary = "Get cities of a country", description = "Returns paginated cities for the given country")
    @ApiResponse(responseCode = "200", description = "Cities fetched successfully")
    @ApiResponse(responseCode = "404", description = "Country not found")
    @GetMapping("/{countryId}/cities")
    public PaginationResponse<City> getCitiesByCountry(
            @PathVariable Long countryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return cityService.getCitiesByCountry(countryId, page, size);
    }
}
