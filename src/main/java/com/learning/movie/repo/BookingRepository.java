package com.learning.movie.repo;

import com.learning.movie.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {

    @Query("select b.id from Booking b where b.showId = :showId")
    List<Long> findAllBookingByShowId(@Param("showId") Long showId);
}