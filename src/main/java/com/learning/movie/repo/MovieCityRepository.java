package com.learning.movie.repo;

import com.learning.movie.model.MovieCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieCityRepository extends JpaRepository<MovieCity, Long>, JpaSpecificationExecutor<MovieCity> {

    @Query("select mc from MovieCity mc where mc.cityId in :cityIds")
    List<MovieCity> findByCityIds(@Param("cityIds") List<Long> cityIds);

    @Query("select mc from MovieCity mc where mc.cityId = :cityId and mc.movieId = :movieId")
    MovieCity findByMovieAndCity(@Param("movieId") Long movieId, @Param("cityId") Long cityId);

    @Query("select mc from MovieCity mc where mc.status = :status")
    List<MovieCity> getActiveMovies(@Param("status") Integer status);

}