package com.learning.movie.redis.service;


import com.learning.movie.model.MovieCityLangView;
import com.learning.movie.redis.repo.MovieCityLangViewCacheRepo;
import com.learning.movie.repo.MovieCityLangViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCityLangViewCacheService {

    @Autowired
    private MovieCityLangViewCacheRepo movieCityLangViewCacheRepo;

    @Autowired
    private MovieCityLangViewRepository movieCityLangViewRepository;

    public List<MovieCityLangView> getShows(Long movieId, Long cityId, Long langId) {

        String showId = movieId + "" + cityId + "" + langId;

        List<MovieCityLangView> shows = movieCityLangViewCacheRepo.findByMovieCityLangIdId(String.valueOf(showId));

        if (shows == null || shows.isEmpty()) {
            shows = movieCityLangViewRepository.findByMovieIdAndCityIdAndLangId(movieId, cityId, langId);
            movieCityLangViewCacheRepo.save(showId, shows);
        }
        return shows;
    }


}
