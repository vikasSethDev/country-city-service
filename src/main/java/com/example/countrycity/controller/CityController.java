package com.example.countrycity.controller;


import com.example.countrycity.dto.PaginationResponse;
import com.example.countrycity.model.City;
import com.example.countrycity.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
@Tag(name = "City", description = "City related APIs")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @Operation(summary = "Get cities by country", description = "Returns paginated cities for the given country")
    @ApiResponse(responseCode = "200", description = "Cities fetched successfully")
    @GetMapping("/country/{countryId}")
    public PaginationResponse<City> getCitiesByCountry(
            @PathVariable Long countryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return cityService.getCitiesByCountry(countryId, page, size);
    }



    @Operation(summary = "Get city by ID", description = "Returns a single city")
    @ApiResponse(responseCode = "200", description = "City found")
    @ApiResponse(responseCode = "404", description = "City not found")
    @GetMapping("/{cityId}")
    public City getCityById(
            @PathVariable Long cityId) {

        return cityService.getCityById(cityId);
    }
}
