package com.learning.movie.repo;

import com.learning.movie.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long>, JpaSpecificationExecutor<ShowSeat> {

    @Modifying
    @Query("delete from ShowSeat sc where sc.bookingId in :bookingIds")
    void deleteAllByBookingIds(@Param("bookingIds") List<Long> bookingIds);

}