package com.learning.movie.repo;

import com.learning.movie.model.Lang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LangRepository extends JpaRepository<Lang, Integer>, JpaSpecificationExecutor<Lang> {

}
