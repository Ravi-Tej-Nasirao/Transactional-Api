package com.learning.movie.repo;

import com.learning.movie.model.MovieCityLangView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieCityLangViewRepository extends JpaRepository<MovieCityLangView, String> {

    @Query("select s from MovieCityLangView s where s.movieId = :movieId and s.cityId = :cityId and s.langId = :langId")
    List<MovieCityLangView> findByMovieIdAndCityIdAndLangId(@Param("movieId") Long movieId, @Param("cityId") Long cityId, @Param("langId") Long langId);
}
