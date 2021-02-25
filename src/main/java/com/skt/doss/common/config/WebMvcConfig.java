package com.skt.doss.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.skt.doss.portal.front.common.interceptor.ConnectNetworkInterceptor;
import com.skt.doss.portal.front.common.interceptor.RequestLoggingInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private RequestLoggingInterceptor requestLoggingInterceptor;
  
  @Autowired
  private ConnectNetworkInterceptor connectNetworkInterceptor;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("*");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index.html");

  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    
    registry.addInterceptor(requestLoggingInterceptor)
    .excludePathPatterns(
        "/css/**",
        "/ext-js/**",
        "/img/**",
        "/js/**",
        "/swagger-ui.html",
        "/v2/api-docs",
        "/swagger-resources/**",
        "/*.ico",
        "/index.html"
        );
    
    registry.addInterceptor(connectNetworkInterceptor)
    .excludePathPatterns(
        "/css/**",
        "/ext-js/**",
        "/img/**",
        "/js/**",
        "/swagger-ui.html",
        "/v2/api-docs",
        "/swagger-resources/**",
        "/*.ico",
        "/index.html"
        );
  }

}
