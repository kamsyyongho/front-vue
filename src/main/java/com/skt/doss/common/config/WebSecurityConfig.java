package com.skt.doss.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.skt.doss.portal.front.common.constant.Role;
import com.skt.doss.portal.front.user.handler.UserAccessDeniedHandler;
import com.skt.doss.portal.front.user.handler.UserAuthEntryPoint;
import com.skt.doss.portal.front.user.handler.UserAuthFailureHandler;
import com.skt.doss.portal.front.user.handler.UserAuthLogoutSuccessHandler;
import com.skt.doss.portal.front.user.handler.UserAuthSuccessHandler;
import com.skt.doss.portal.front.user.provider.UserAuthProvider;
import com.skt.doss.portal.front.user.service.DossUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserAuthProvider userAuthProvider;

  @Autowired
  private UserAuthSuccessHandler userAuthSuccessHandler;

  @Autowired
  private UserAuthFailureHandler userAuthFailureHandler;

  @Autowired
  private UserAccessDeniedHandler userAccessDeniedHandler;

  @Autowired
  private DossUserDetailsService dossUserDetailsService;

  @Autowired
  private UserAuthLogoutSuccessHandler userAuthLogoutSuccessHandler;
  
  @Autowired
  private UserAuthEntryPoint userAuthEntryPoint;
  

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(userAuthProvider);
    //          .userDetailsService(dossUserDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable();

    http.authorizeRequests()
      .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
      .antMatchers(
          "/api/**",
          "/error",
          "/error/**",
          "/login",
          "/login-ldap",
          "/join"
          ).permitAll()
      .anyRequest().authenticated();

//    http.formLogin()
//      .loginPage("/login")
//      .usernameParameter("userId")
//      .passwordParameter("userPw")
//      .successHandler(userAuthSuccessHandler)
//      .failureHandler(userAuthFailureHandler);
    
//    http.logout()
//      .logoutUrl("/logout")
//      .logoutSuccessHandler(userAuthLogoutSuccessHandler);
    
//    http.authenticationProvider(userAuthProvider);
    
//    http.exceptionHandling()
//      .authenticationEntryPoint(userAuthEntryPoint);
    
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(
        "/css/**",
        "/ext-js/**",
        "/img/**",
        "/js/**",
        "/swagger-ui.html",
        "/v2/api-docs",
        "/swagger-resources/**"
        );
  }

}
