package com.skt.doss.portal.front.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtil {
  
  public static ServletRequestAttributes getServletRequestAttributes() {
    return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
  }
  
  public static String getSessionId() {
    return getServletRequestAttributes().getSessionId();
  }
  
  public static HttpServletRequest getHttpServletRequest() {
    return getServletRequestAttributes().getRequest();
  }
  
  public static HttpSession getSession() {
    return getHttpServletRequest().getSession();
  }
  
  public static String getClientIp(HttpServletRequest request) {
    
    String clientIp = request.getHeader("X-Forwarded-For");
    
    if (!StringUtils.hasText(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = request.getHeader("Proxy-Client-IP");
    }
    if (!StringUtils.hasText(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = request.getHeader("WL-Proxy-Client-IP");
    }
    if (!StringUtils.hasText(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = request.getHeader("HTTP_CLIENT_IP");
    }
    if (!StringUtils.hasText(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (!StringUtils.hasText(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
      clientIp = request.getRemoteAddr();
    }
    
    return clientIp;
  }
}
