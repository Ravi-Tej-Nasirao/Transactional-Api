package com.learning.movie.repo;

import com.learning.movie.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TheatreRepository extends JpaRepository<Theatre, Long>, JpaSpecificationExecutor<Theatre> {

    @Query("select t from Theatre t where t.id = :theatreId and t.status = :status")
    Theatre findByTheatreIdAndStatus(@Param("theatreId") Long theatreId, @Param("status") Boolean status);

}