package com.skt.doss.common.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class ApiHeaderConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new AuthRequestInterceptor();
    }
}
