package com.learning.movie.controller;

import com.learning.movie.model.City;
import com.learning.movie.model.Movie;
import com.learning.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/v1/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public void addMovie(@RequestBody(required = true) Movie movie) {
        movieService.addMovie(movie);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable("movieId") Long movieId) {
        Movie movie = movieService.deleteMovie(movieId);
        if (Objects.nonNull(movie)) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping
    public ResponseEntity permitMovieForState(@RequestParam Long stateId, @RequestParam Integer status) {
        boolean isPermissionsApplied = movieService.permitMovieForState(stateId, status);
        if (isPermissionsApplied) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
