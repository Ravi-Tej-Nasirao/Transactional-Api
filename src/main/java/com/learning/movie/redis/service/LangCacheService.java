package com.learning.movie.redis.service;


import com.learning.movie.model.Lang;
import com.learning.movie.redis.repo.LangCacheRepo;
import com.learning.movie.repo.LangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LangCacheService {

    @Autowired
    private LangRepository langRepository;

    @Autowired
    private LangCacheRepo langCacheRepo;

    public void loadLangCache() {

        List<Lang> languages = langRepository.findAll();
        languages.forEach(lang -> {
            langCacheRepo.save(lang.getId(), lang);
        });

    }
}
