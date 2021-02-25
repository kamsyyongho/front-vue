package com.skt.doss.portal.front.user.provider;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.skt.doss.portal.front.api_gateway.v1.domain.CheckDossUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.CrowdUserToken;
import com.skt.doss.portal.front.api_gateway.v1.domain.CrowdUserYn;
import com.skt.doss.portal.front.api_gateway.v1.domain.LdapCheckDossUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.SktMemberYn;
import com.skt.doss.portal.front.common.constant.Role;
import com.skt.doss.portal.front.user.domain.DossUserDetails;
import com.skt.doss.portal.front.user.service.UserService;

@Component
public class UserAuthProvider implements AuthenticationProvider {

  private static final Logger log = LoggerFactory.getLogger(UserAuthProvider.class);

  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String principal = (String)authentication.getPrincipal();
    String credentials = (String)authentication.getCredentials();
    
    log.debug("!@# principal: {}", principal);
    log.debug("!@# credentials: {}", credentials);

    DossUserDetails userDetails = (DossUserDetails)authentication.getDetails();
    log.debug("!@# isLdapLogin: {}", userDetails.getIsLdapLogin());
    
    if (userDetails.getIsLdapLogin()) {
      userDetails.setIsDossUser(ldapCheckDossUser(principal, credentials));
    } else {
      userDetails.setIsDossUser(checkDossUser(principal, credentials));
    }

    userDetails.setIsSktUser(checkSktMember(principal));
    
    if (!userDetails.getIsDossUser()) {
      log.debug("!@# userId({}) not found.", principal);
      throw new UsernameNotFoundException(null);
    }

    List<GrantedAuthority> authorities = new ArrayList<>();
    
    if (userDetails.getIsSktUser()) {
      authorities.add(new SimpleGrantedAuthority(Role.SKT));
    } else {
      authorities.add(new SimpleGrantedAuthority(Role.BP));
    }
    
    CrowdUserYn.Res crowdUserYnRes = userService.crowdUserYn((String)authentication.getPrincipal());
    if (crowdUserYnRes != null && "OK".equals(crowdUserYnRes.getCode()) && "Y".equals(crowdUserYnRes.getUserYn())) {
      CrowdUserToken.Req crowdUserTokenReq = CrowdUserToken.Req.builder()
          .userId((String)authentication.getPrincipal())
          .userPw((String)authentication.getCredentials())
          .build();
      CrowdUserToken.Res crowdUserTokenRes = userService.crowdUserToken(crowdUserTokenReq);
      if (crowdUserTokenRes != null && "OK".equals(crowdUserTokenRes.getCode()) && StringUtils.hasText(crowdUserTokenRes.getToken())) {
        userDetails.setCrowdToken(crowdUserTokenRes.getToken());
      }
    }
    
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
    userDetails.setUserPw(null);
    authenticationToken.setDetails(userDetails);
    authenticationToken.eraseCredentials();

    return authenticationToken;
  }
  

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }


  private boolean checkDossUser(String dossId, String pwd) {
    CheckDossUser.Res resCheckDossUser = userService.checkDossUser(
        CheckDossUser.Req.builder()
        .dossId(dossId)
        .pwd(pwd)
        .build()
        );
    log.debug("!@# resCheckDossUser: {}", resCheckDossUser);
    if (resCheckDossUser != null && "Y".equals(resCheckDossUser.getResult())) {
      return true;
    }
    return false;
  }


  private boolean checkSktMember(String empno) {
    SktMemberYn.Res resSktMemberYn = userService.sktMemberYn(empno);
    log.debug("!@# resSktMemberYn: {}", resSktMemberYn);
    if (resSktMemberYn != null && "Y".equals(resSktMemberYn.getSktMemberYn())) {
      return true;
    }
    return false;
  }
  
  private boolean ldapCheckDossUser(String dossId, String pwd) {
    LdapCheckDossUser.Res resLdapCheckDossUser = userService.ldapCheckDossUser(
        LdapCheckDossUser.Req.builder()
        .dossId(dossId)
        .pwd(pwd)
        .build()
        );
    log.debug("!@# resLdapCheckDossUser: {}", resLdapCheckDossUser);
    if (resLdapCheckDossUser != null && "Y".equals(resLdapCheckDossUser.getResult())) {
      return true;
    }
    return false;
  }

}
