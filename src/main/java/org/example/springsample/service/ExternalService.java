package org.example.springsample.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    @CircuitBreaker(name = "myInstance", fallbackMethod = "fallBackMethod")
    public String makeExternalCall() {
        return "External Call";
    }

    public String fallBackMethod() {
        return "From fallback method";
    }

    @Retry(name = "myRetryInstance", fallbackMethod = "databaseFallBack")
    public String databaseCall() {
        return "From database method";
    }

    public String databaseFallBack() {
        return "From database fallback method";
    }

    @RateLimiter(name = "rateLimiterInstance", fallbackMethod = "rateLimiterFallback")
    public String rateLimitedCall() {
        return "From rateLimiter fallback method";
    }

    public String rateLimiterFallback() {
        return "From rateLimiter fallback method";
    }

    /**
     * The Bulkhead pattern is used to isolate different parts of a system to prevent a failure in one part from cascading to the rest of the system.
     */
    @Bulkhead(name = "bulkHeadInstance", type = Bulkhead.Type.THREADPOOL)
    public String bulkheadIsolatedMethod() {
        return "From bulkhead isolated method";
    }

}
