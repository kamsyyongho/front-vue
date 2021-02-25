package com.skt.doss.portal.front.user.handler;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class UserAuthFailureHandler implements AuthenticationFailureHandler {
    
  private static final Logger log = LoggerFactory.getLogger(UserAuthFailureHandler.class);

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {
    
    log.debug("!@# onAuthenticationFailure");
    
//    request.setAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
    request.getRequestDispatcher("/login").forward(request, response);
//    Map<String, String[]> parameterMap = request.getParameterMap();
//    parameterMap.put("error", new String[]{"401"});
//    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다.");
  }
}
