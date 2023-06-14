package com.learning.movie.scheduler;

import com.learning.movie.redis.service.LoadInitCacheService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class ScheduledTask {

    @Autowired
    private LoadInitCacheService loadInitCacheService;

    @Scheduled(cron = "${schedule.cron}", zone = "${schedule.timezone}") // cron every min - 0 * * * * ?
    @SchedulerLock(name = "loadMovieCache", lockAtMostFor = "2000s", lockAtLeastFor = "1000s")
    public void loadMovieCache() {
        loadInitCacheService.loadCache();
    }

}
