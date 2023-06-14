package com.learning.movie.repo;

import com.learning.movie.model.ShowPromo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowPromoRepository extends JpaRepository<ShowPromo, Long> {

    @Query("select s from ShowPromo s where s.showId = :showId and s.status = :status")
    List<ShowPromo> findByShowIdAndStatus(@Param("showId") Long showId, @Param("status") Integer status);

    @Query("select s from ShowPromo s where s.status = :status")
    List<ShowPromo> getActivePromos(@Param("status") Integer status);
}
