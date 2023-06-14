package com.learning.movie.service;

import com.learning.movie.model.City;
import com.learning.movie.model.MovieCity;
import com.learning.movie.repo.CityRepository;
import com.learning.movie.repo.MovieCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private MovieCityRepository movieCityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAllByStatus(1);
    }

    public City findCityById(Long cityId) {
        return cityRepository.findByIdAndStatus(cityId, 1);
    }

    public City addCity(@RequestBody City city) {
        return cityRepository.save(new City(city.getName(), city.getStateId()));
    }

    public MovieCity addMovieForCity(MovieCity movieCity) {
        return movieCityRepository.save(movieCity);
    }

    public MovieCity deleteMovieForCity(MovieCity movieCity) {
        MovieCity movieCityDb = movieCityRepository.findByMovieAndCity(movieCity.getMovieId(), movieCity.getCityId());
        if (Objects.nonNull(movieCityDb)) {
            movieCityDb.setStatus(0);
            return movieCityRepository.save(movieCityDb);
        }
        return null;
    }

    public List<MovieCity> addMovieForCities(List<MovieCity> movieCities) {
        return movieCityRepository.saveAll(movieCities);
    }

}
