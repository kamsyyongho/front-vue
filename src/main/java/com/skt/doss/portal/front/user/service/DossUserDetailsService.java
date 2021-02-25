package com.skt.doss.portal.front.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DossUserDetailsService implements UserDetailsService {
    
  private static final Logger log = LoggerFactory.getLogger(DossUserDetailsService.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    log.debug("!@# username: {}", username);
    
    return null;
  }
    

  
}
