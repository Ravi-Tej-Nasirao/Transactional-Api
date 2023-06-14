package com.learning.movie.controller;

import com.learning.movie.model.City;
import com.learning.movie.model.MovieCity;
import com.learning.movie.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city) {
        try {
            City cityDb = cityService
                    .addCity(new City(city.getName(), city.getStateId()));
            return new ResponseEntity<>(cityDb, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findCityById(@PathVariable("id") Long cityId) {
        City city = cityService.findCityById(cityId);
        if (Objects.nonNull(city)) {
            return new ResponseEntity<>(city, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/movie")
    public ResponseEntity<MovieCity> addMovieForCity(@RequestBody MovieCity movieCity) {
        movieCity = cityService.addMovieForCity(movieCity);
        return new ResponseEntity<>(movieCity, HttpStatus.CREATED);
    }

    @PostMapping("/movies")
    public ResponseEntity<List<MovieCity>> addMovieForCity(@RequestBody List<MovieCity> movieCities) {
        movieCities = cityService.addMovieForCities(movieCities);
        return new ResponseEntity<>(movieCities, HttpStatus.CREATED);
    }

    @DeleteMapping("/movie")
    public ResponseEntity<MovieCity> deleteMovieForCity(@RequestBody MovieCity movieCity) {
        movieCity = cityService.deleteMovieForCity(movieCity);
        if (Objects.nonNull(movieCity)) {
            return new ResponseEntity<>(movieCity, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
