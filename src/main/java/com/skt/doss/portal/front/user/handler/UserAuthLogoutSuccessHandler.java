package com.skt.doss.portal.front.user.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import com.skt.doss.portal.front.common.constant.DossCookie;

@Component
public class UserAuthLogoutSuccessHandler implements LogoutSuccessHandler {

  private static final Logger log = LoggerFactory.getLogger(UserAuthLogoutSuccessHandler.class);

  @Override
  public void onLogoutSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
          throws IOException, ServletException {

    log.debug("!@# UserAuthLogoutSuccessHandler");

    Cookie crowdTokenCookie = new Cookie(DossCookie.CROWD_TOKEN_KEY, null);
    crowdTokenCookie.setMaxAge(0);
    crowdTokenCookie.setPath("/");
    response.addCookie(crowdTokenCookie);
    
    response.sendRedirect("/login");
  }  



}
