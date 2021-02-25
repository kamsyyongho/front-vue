package com.skt.doss.portal.front.common.interceptor;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

  private static final Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession();

    // session
    String sessionInfo = "session_id=" + session.getId();

    // url
    StringBuffer sb = new StringBuffer();
    sb.append("request_url=").append(request.getRequestURL());
    String queryString = request.getQueryString();
    if (queryString != null) {
      sb.append("?").append(queryString);
    }
    String urlInfo = sb.toString();

    // header
    sb.setLength(0);
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      sb.append(headerName).append("=").append(request.getHeader(headerName)).append("\n");
    }
    String headerInfo = sb.toString();

    // parameter
    sb.setLength(0);
    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String parameterName = parameterNames.nextElement();
      String[] parameterValues = request.getParameterValues(parameterName);
      for (String parameterValue : parameterValues) {
        sb.append(parameterName).append("=").append(parameterValue).append("\n");
      }
    }
    String parameterInfo = sb.toString();

    log.info(
        "\n" + "##############################   <REQUEST_INFO>   ##############################\n"
            + "================================  SESSION  =====================================\n"
            + "{}\n"
            + "================================  URL  =========================================\n"
            + "{}\n"
            + "================================  HEADER  ======================================\n"
            + "{}"
            + "================================  PARAMETER  ===================================\n"
            + "{}"
            + "##############################   </REQUEST_INFO>   #############################\n",
        sessionInfo, urlInfo, headerInfo, parameterInfo);

    request.setAttribute("uriInfo", request.getRequestURI());
    
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    
  }

}
