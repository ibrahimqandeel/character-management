package com.rakuten.challenge.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClearCacheTask {
    private CacheManager cacheManager;

    public ClearCacheTask(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    // reset cache every hr, with delay of 1hr after app start
    @Scheduled(fixedRateString = "${cache.evict.rate.seconds}", initialDelayString = "${cache.evict.delay.seconds}")
    public void scheduleCacheEvict() {
        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
        log.info("Clear Cache Every 1 Hour");
    }
}
