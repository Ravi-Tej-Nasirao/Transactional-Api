package com.learning.movie.redis.service;


import com.learning.movie.constants.Constants;
import com.learning.movie.model.Movie;
import com.learning.movie.model.MovieCity;
import com.learning.movie.redis.repo.MoviesOfCityCacheRepo;
import com.learning.movie.repo.CityRepository;
import com.learning.movie.repo.MovieCityRepository;
import com.learning.movie.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieCacheService {
    @Autowired
    private MovieCityRepository movieCityRepository;

    @Autowired
    private MoviesOfCityCacheRepo movieCacheRepo;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CityRepository cityRepository;

    public List<Movie> getMoviesByCityId(Long cityId) {
        return movieCacheRepo.findByCityId(cityId);
    }

    public void loadMoviesByCity() {

        List<MovieCity> movieCities = movieCityRepository.getActiveMovies(Constants.ACTIVE);

        Map<Long, List<Long>> map =
                movieCities.stream().map(movieCity -> new AbstractMap.SimpleEntry<>(movieCity.getCityId(), movieCity.getMovieId())) // Stream<Map.Entry<Obligation,String>>
                        .collect(Collectors.groupingBy(Map.Entry::getKey,
                                Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        Map<Long, List<Movie>> moviesByCity = new HashMap<>();

        map.entrySet().stream().forEach(entry -> {
            List<Movie> movies = movieRepository.findAllById(entry.getValue());
            moviesByCity.put(entry.getKey(), movies);
        });

        movieCacheRepo.saveAllMovies(moviesByCity);

    }

}
