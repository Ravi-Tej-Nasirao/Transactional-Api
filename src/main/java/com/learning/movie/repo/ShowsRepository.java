package com.learning.movie.repo;

import com.learning.movie.model.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowsRepository extends JpaRepository<Shows, Long>, JpaSpecificationExecutor<Shows> {

    @Query("select s from Shows s where " +
            "(s.startTime <= :startTime and s.endTime >= :startTime and s.auditoriumId = :auditoriumId)" +
            "or" +
            "(s.startTime <= :endTime and s.endTime >= :endTime and s.auditoriumId = :auditoriumId)")
    List<Shows> getConflictingTimeRecords(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime,
                                          @Param("auditoriumId") Long auditoriumId);
}