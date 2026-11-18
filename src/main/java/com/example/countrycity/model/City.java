package com.example.countrycity.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private Long id;
    private String name;
    private Long countryId;
    private Long population;
    private String  description;

}
