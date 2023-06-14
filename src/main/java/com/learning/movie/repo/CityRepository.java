package com.learning.movie.repo;


import com.learning.movie.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

    @Query("select c from City c where c.id = :cityId and c.status = :status")
    City findByIdAndStatus(@Param("cityId") Long cityId, @Param("status") Integer status);

    @Query("select c from City c where c.status = :status")
    List<City> findAllByStatus(@Param("status") Integer status);

    @Query("select c from City c where c.stateId = :stateId")
    List<City> findAllByStateId(@Param("stateId") Long stateId);
}