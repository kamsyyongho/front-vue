package com.skt.doss.portal.front.user.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@SuppressWarnings("serial")
public class DossUserDetails implements Serializable {
//public class DossUserDetails implements UserDetails {

  private String userId;
  
  private String userPw;
  
  private Boolean isLdapLogin;
  
//  private Collection<GrantedAuthority> authorities;
  
  private Boolean isDossUser;
  
  private Boolean isSktUser;
  
  private String dossClientNetwork;
  
  private String crowdToken;
  
//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return authorities;
//  }
//
//  @Override
//  public String getPassword() {
//    return userPw;
//  }
//
//  @Override
//  public String getUsername() {
//    return userId;
//  }
//
//  @Override
//  public boolean isAccountNonExpired() {
//    // TODO Auto-generated method stub
//    return false;
//  }
//
//  @Override
//  public boolean isAccountNonLocked() {
//    // TODO Auto-generated method stub
//    return false;
//  }
//
//  @Override
//  public boolean isCredentialsNonExpired() {
//    // TODO Auto-generated method stub
//    return false;
//  }
//
//  @Override
//  public boolean isEnabled() {
//    // TODO Auto-generated method stub
//    return false;
//  }
}
