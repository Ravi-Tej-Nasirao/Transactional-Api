package com.learning.movie.redis.repo;


import com.learning.movie.model.MovieCityLangView;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class MovieCityLangViewCacheRepo {

    private final String hashReference = "MovieCityLangView";

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, List<MovieCityLangView>> hashOperations;

    public void save(String movieCityLangId, List<MovieCityLangView> movieCityLangRecords) {
        //creates one record in Redis DB if record with that Id is not present
        hashOperations.putIfAbsent(hashReference, movieCityLangId, movieCityLangRecords);
    }

    public List<MovieCityLangView> findByMovieCityLangIdId(String movieCityLangId) {
        return hashOperations.get(hashReference, movieCityLangId);
    }

}
