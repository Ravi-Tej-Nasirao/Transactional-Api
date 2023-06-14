package com.learning.movie.repo;

import com.learning.movie.model.Casting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CastingRepository extends JpaRepository<Casting, Long>, JpaSpecificationExecutor<Casting> {

}