package com.skt.doss.portal.front.user.handler;

import java.io.IOException;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.skt.doss.portal.front.common.constant.DossCookie;
import com.skt.doss.portal.front.common.constant.Network;
import com.skt.doss.portal.front.common.constant.Role;
import com.skt.doss.portal.front.user.domain.DossUserDetails;

@Component
public class UserAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {  

  private static final Logger log = LoggerFactory.getLogger(UserAuthSuccessHandler.class);

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

    HttpSession session = request.getSession();

    log.debug("!@# onAuthenticationSuccess");
    String redirectUrl = "/fido-auth/" + (String)authentication.getPrincipal();

    log.debug("!@# CLIENT_NETWORK: {}", session.getAttribute(Network.CLIENT_NETWORK_ATTRIBUTE));

    DossUserDetails userDetails = (DossUserDetails)authentication.getDetails();
    userDetails.setDossClientNetwork((String)session.getAttribute(Network.CLIENT_NETWORK_ATTRIBUTE));

    if (StringUtils.hasText(userDetails.getCrowdToken())) {


      Cookie crowdTokenCookie = new Cookie(DossCookie.CROWD_TOKEN_KEY, userDetails.getCrowdToken());
      crowdTokenCookie.setMaxAge(-1);
      crowdTokenCookie.setPath("/");
//      crowdTokenCookie.setDomain("doss.sktelecom.com");
      crowdTokenCookie.setHttpOnly(true);
      response.addCookie(crowdTokenCookie);
    }

    Iterator<? extends GrantedAuthority> iterator = authentication.getAuthorities().iterator();
    while (iterator.hasNext()) {
      GrantedAuthority grantedAuthority = (GrantedAuthority) iterator.next();
      if (grantedAuthority.getAuthority().equals(Role.SKT)) {
        redirectUrl = "/";
      } 

    }



    //    CrowdUserYn.Res crowdUserYnRes = userService.crowdUserYn((String)authentication.getPrincipal());
    //    log.debug("!@#1");
    //    if (crowdUserYnRes != null && "OK".equals(crowdUserYnRes.getCode()) && "Y".equals(crowdUserYnRes.getUserYn())) {
    //      log.debug("!@#1");
    //      CrowdUserToken.Req crowdUserTokenReq = CrowdUserToken.Req.builder()
    //          .userId((String)authentication.getPrincipal())
    //          .userPw((String)authentication.getCredentials())
    //          .build();
    //      CrowdUserToken.Res crowdUserTokenRes = userService.crowdUserToken(crowdUserTokenReq);
    //      if (crowdUserTokenRes != null && "OK".equals(crowdUserTokenRes.getCode()) && StringUtils.hasText(crowdUserTokenRes.getToken())) {
    //        Cookie tokenCookie = new Cookie(DossCookie.CROWD_TOKEN_KEY, crowdUserTokenRes.getToken());
    //        tokenCookie.setMaxAge(-1);
    //        response.addCookie(tokenCookie);
    //      }
    //    }

    //    String dossClientNetwork = (String)session.getAttribute(Network.CLIENT_NETWORK_ATTRIBUTE);
    //    if (Network.EXT.equals(dossClientNetwork)) {
    //      redirectUrl = "/fido-auth/" + (String)authentication.getPrincipal();
    //    }

    getRedirectStrategy().sendRedirect(request, response, redirectUrl);

  }


}