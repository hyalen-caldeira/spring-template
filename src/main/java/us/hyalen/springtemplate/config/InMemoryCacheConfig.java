package us.hyalen.springtemplate.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.hyalen.springtemplate.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class InMemoryCacheConfig {
    private int paymentParameterCacheExpiration;
    private int paymentParameterCacheMaxSize;

    private static final TimeUnit CACHE_EXPIRATION_TIME_UNIT = TimeUnit.MINUTES;

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(buildLocalCaches());

        return simpleCacheManager;
    }

    private List<Cache> buildLocalCaches() {
        List<Cache> caches = new ArrayList<>();

        caches.add(
                new ConcurrentMapCache(
                        Constants.PAYMENT_PARAMETER_CACHE_NAME,
                        CacheBuilder.newBuilder()
                                .expireAfterWrite(paymentParameterCacheExpiration, CACHE_EXPIRATION_TIME_UNIT)
                                .maximumSize(paymentParameterCacheMaxSize)
                                .build()
                                .asMap(),
                        false
                ));

        return caches;
    }
}
