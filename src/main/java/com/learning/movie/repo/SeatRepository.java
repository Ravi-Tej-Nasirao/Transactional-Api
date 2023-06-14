package com.learning.movie.repo;

import com.learning.movie.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long>, JpaSpecificationExecutor<Seat> {

    @Query("select s from Seat s where s.auditoriumId = :auditoriumId")
    List<Seat> getSeatsForAuditorium(@Param("auditoriumId") Long auditoriumId);


}