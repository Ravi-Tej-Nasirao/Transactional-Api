package com.learning.movie.redis.repo;


import com.learning.movie.model.Movie;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class MoviesOfCityCacheRepo {

    private final String hashReference = "Movie";

    @Resource(name = "redisTemplate")
    private HashOperations<String, Long, List<Movie>> hashOperations;

    public void save(Long cityId, List<Movie> movies) {
        //creates one record in Redis DB if record with that Id is not present
        hashOperations.put(hashReference, cityId, movies);
    }

    public void saveAllMovies(Map<Long, List<Movie>> movies) {
        hashOperations.putAll(hashReference, movies);
    }

    public List<Movie> findByCityId(Long cityId) {
        return hashOperations.get(hashReference, cityId);
    }

    public void deleteByCityId(Long cityId) {
        hashOperations.delete(hashReference, cityId);
    }

}
