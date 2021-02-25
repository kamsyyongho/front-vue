package com.skt.doss.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

@Configuration
public class CacheConfig {

  @Bean
  ConfigureRedisAction configureRedisAction() {
    return ConfigureRedisAction.NO_OP;
  }

}
