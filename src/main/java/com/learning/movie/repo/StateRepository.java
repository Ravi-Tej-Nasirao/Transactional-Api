package com.learning.movie.repo;

import com.learning.movie.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StateRepository extends JpaRepository<State, Integer>, JpaSpecificationExecutor<State> {

}