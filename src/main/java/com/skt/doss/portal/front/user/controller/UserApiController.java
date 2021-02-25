package com.skt.doss.portal.front.user.controller;

import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skt.doss.portal.front.api_gateway.v1.domain.AddCrowdUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.CertiAsisUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.CrowdUserToken;
import com.skt.doss.portal.front.api_gateway.v1.domain.CrowdUserYn;
import com.skt.doss.portal.front.api_gateway.v1.domain.DeleteCrowdUserToken;
import com.skt.doss.portal.front.api_gateway.v1.domain.FidoAuthentication;
import com.skt.doss.portal.front.api_gateway.v1.domain.GetSktMemberInfo;
import com.skt.doss.portal.front.api_gateway.v1.domain.LdapCheckDossUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.MakeDossId;
import com.skt.doss.portal.front.api_gateway.v1.domain.SerchUserInfo;
import com.skt.doss.portal.front.api_gateway.v1.domain.SignUp;
import com.skt.doss.portal.front.api_gateway.v1.domain.SktMemberYn;
import com.skt.doss.portal.front.common.constant.DossCookie;
import com.skt.doss.portal.front.user.domain.DossUserDetails;
import com.skt.doss.portal.front.user.service.UserService;

@RestController
public class UserApiController {
  
  private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @PostMapping("/api/login")
  public ResponseEntity<UsernamePasswordAuthenticationToken> login(@RequestBody DossUserDetails userDetails, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

    log.debug("!@# dossUserDetails: {}", userDetails.toString());
    //    log.debug("!@# userNetwork: {}", session.getAttribute(Network.CLIENT_NETWORK_ATTRIBUTE));
    //    dossUserDetails.setDossClientNetwork((String)session.getAttribute(Network.CLIENT_NETWORK_ATTRIBUTE));

    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUserId(), userDetails.getUserPw());
    token.setDetails(userDetails);

    //    Authentication authentication = authenticationManager.authenticate(token);

    token = (UsernamePasswordAuthenticationToken)authenticationManager.authenticate(token);
    userDetails = (DossUserDetails)token.getDetails();
    
    if (userDetails != null && StringUtils.hasText(userDetails.getCrowdToken())) {
      Cookie crowdTokenCookie = new Cookie(DossCookie.CROWD_TOKEN_KEY, userDetails.getCrowdToken());
      crowdTokenCookie.setMaxAge(-1);
      crowdTokenCookie.setPath("/");
      crowdTokenCookie.setHttpOnly(true);
      response.addCookie(crowdTokenCookie);
    }

    SecurityContextHolder.getContext().setAuthentication(token);
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

    return ResponseEntity.ok(token);
  }

  @GetMapping("/api/logout")
  public ResponseEntity<UsernamePasswordAuthenticationToken> logout(HttpServletRequest request, HttpServletResponse response, HttpSession session, @CookieValue("crowd.token_key") String crowdToken) throws Exception {
    
    SecurityContextHolder.clearContext();
    
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        cookie.setValue(null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
      }
    }
    
    log.debug("!@# crowdToken: {}", crowdToken);
    if (StringUtils.hasText(crowdToken)) {
      try {
        DeleteCrowdUserToken.Res res = userService.deleteCrowdUserToken(crowdToken);
        log.debug("!@# DeleteCrowdUserToken.Res: {}", res.toString());
      } catch (Exception e) {
        log.error("!@# DeleteCrowdUserToken error: ", e);
      }
    }
    
    session.invalidate();
    
    log.debug("!@# logout");
    
    return ResponseEntity.ok(null);
  }

  @GetMapping("/api/user/makeDossId")
  public ResponseEntity<MakeDossId.Res> makeDossId() throws Exception {
    return ResponseEntity.ok(userService.makeDossId());
  }

  @PostMapping("/api/user/signUp")
  public ResponseEntity<SignUp.Res> signUp(@RequestBody SignUp.Req req) throws Exception {
    return ResponseEntity.ok(userService.signUp(req));
  }

  @GetMapping("/api/user/sktMemberYn/{empno}")
  public ResponseEntity<SktMemberYn.Res> sktMemberYn(@PathVariable String empno) throws Exception {
    return ResponseEntity.ok(userService.sktMemberYn(empno));
  }

  @GetMapping("/api/user/fidoAuthentication/{dossId}")
  public ResponseEntity<FidoAuthentication.Res> fidoAuthentication(@PathVariable String dossId) {
    return ResponseEntity.ok(userService.fidoAuthentication(dossId));
  }

  @GetMapping("/api/user/crowd/user-yn")
  public ResponseEntity<CrowdUserYn.Res> crowdUserYn(@RequestParam String userId) {
    return ResponseEntity.ok(userService.crowdUserYn(userId));
  }

  @PostMapping("/api/user/crowd/user-token")
  public ResponseEntity<CrowdUserToken.Res> crowdUserToken(@RequestBody CrowdUserToken.Req req) {
    return ResponseEntity.ok(userService.crowdUserToken(req));
  }
  
  @DeleteMapping("/api/user/crowd/delete-user-token/{token}")
  public ResponseEntity<DeleteCrowdUserToken.Res> deleteCrowdUserToken(@PathVariable String token) {
    return ResponseEntity.ok(userService.deleteCrowdUserToken(token));
  }

  @PostMapping("/api/user/certiAsisUser")
  public ResponseEntity<CertiAsisUser.Res> certiAsisUser(@RequestBody CertiAsisUser.Req req) {
    return ResponseEntity.ok(userService.certiAsisUser(req));
  }

  @PostMapping("/api/user/crowd/add-crowd-user")
  public ResponseEntity<AddCrowdUser.Res> addCrowdUser(@RequestBody AddCrowdUser.Req req) {
    return ResponseEntity.ok(userService.addCrowdUser(req));
  }

  @GetMapping("/api/user/getSktMemberInfo/{empno}")
  public ResponseEntity<GetSktMemberInfo.Res> getSktMemberInfo(@PathVariable String empno) {
    return ResponseEntity.ok(userService.getSktMemberInfo(empno));
  }

  @GetMapping("/api/user/searchUserInfo/{dossId}")
  public ResponseEntity<SerchUserInfo.res> searchUserInfo(@PathVariable String dossId) {
    return ResponseEntity.ok(userService.searchUserInfo(dossId));
  }
  
  @PostMapping("/api/ldap/checkDossUser")
  public ResponseEntity<LdapCheckDossUser.Res> ldapCheckDossUser(@RequestBody LdapCheckDossUser.Req req) {
    return ResponseEntity.ok(userService.ldapCheckDossUser(req));
  }
}
