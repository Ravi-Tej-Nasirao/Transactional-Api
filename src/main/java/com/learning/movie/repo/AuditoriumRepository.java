package com.learning.movie.repo;

import com.learning.movie.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Long>, JpaSpecificationExecutor<Auditorium> {

}