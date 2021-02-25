package com.skt.doss;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
@EnableFeignClients
@SpringBootApplication
public class DossPortalFrontApplication {

  private static final String PROPERTIES =
      "spring.config.location="
          + "classpath:/config/application/";

  public static void main(String[] args) {
    new SpringApplicationBuilder(DossPortalFrontApplication.class)
    .properties(PROPERTIES)
    .run(args);
  }
}
