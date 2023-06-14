package com.learning.movie.config;

import com.learning.movie.redis.service.LoadInitCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppInitializeTask implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private LoadInitCacheService loadInitCacheService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadInitCacheService.loadCache();
    }
}
