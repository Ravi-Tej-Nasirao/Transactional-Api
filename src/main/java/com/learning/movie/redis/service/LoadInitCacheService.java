package com.learning.movie.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadInitCacheService {

    @Autowired
    private LangCacheService langCacheService;

    @Autowired
    private MovieCacheService movieCacheService;

    @Autowired
    private ShowPromoCacheService showPromoCacheService;

    public void loadCache() {
        langCacheService.loadLangCache();
        movieCacheService.loadMoviesByCity();
        showPromoCacheService.loadPromosByShowId();
    }


}
