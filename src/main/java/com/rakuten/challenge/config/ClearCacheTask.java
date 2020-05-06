package com.rakuten.challenge.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class ClearCacheTask {
    private final CacheManager cacheManager;

    public ClearCacheTask(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Scheduled(fixedRateString = "${cache.evict.rate.seconds}", initialDelayString = "${cache.evict.delay.seconds}")
    public void scheduleCacheEvict() {
        cacheManager.getCacheNames().parallelStream().forEach(name -> Objects.requireNonNull(cacheManager.getCache(name)).clear());
        log.info("Clearing Cache");
    }
}
