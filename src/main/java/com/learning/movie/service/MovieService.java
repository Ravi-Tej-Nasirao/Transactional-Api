package com.learning.movie.service;


import com.learning.movie.constants.Constants;
import com.learning.movie.model.City;
import com.learning.movie.model.Movie;
import com.learning.movie.model.MovieCity;
import com.learning.movie.redis.repo.MovieCacheRepo;
import com.learning.movie.repo.CityRepository;
import com.learning.movie.repo.MovieCityRepository;
import com.learning.movie.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieCacheRepo movieCacheRepo;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private MovieCityRepository movieCityRepository;

    public List<Movie> getMoviesByCityId(Long cityId) {

        List<Movie> moviesOfCity = movieCacheRepo.findByCityId(cityId);

        if (Objects.isNull(moviesOfCity)) {
            throw new EntityNotFoundException("Movies are not found for the city.");
        }
        return moviesOfCity;
    }

    public void addMovie(Movie movie) {
        Movie cinema = movieRepository.save(movie);
        List<MovieCity> movieCities = movie.getCityId().stream().map(cityId -> new MovieCity(cinema.getId(), cityId)).collect(Collectors.toList());
        movieCityRepository.saveAll(movieCities);
    }


    public Movie deleteMovie(Long movieId) {
        Movie movie = movieRepository.getById(movieId);
        if (Objects.nonNull(movie)) {
            movie.setStatus(Constants.INACTIVE);
            return movieRepository.save(movie);
        }
        return null;
    }

    public boolean permitMovieForState(Long stateId, Integer status) {
        List<City> cities = cityRepository.findAllByStateId(stateId);
        if(cities == null || !cities.isEmpty()){
            return false;
        }
        List<Long> cityIds = cities.stream().map(city -> city.getId()).collect(Collectors.toList());
        List<MovieCity> movieCities = movieCityRepository.findByCityIds(cityIds);
        movieCities.forEach(movieCity -> {
            movieCity.setStatus(status);
        });
        movieCityRepository.saveAll(movieCities);
        return true;
    }
}
