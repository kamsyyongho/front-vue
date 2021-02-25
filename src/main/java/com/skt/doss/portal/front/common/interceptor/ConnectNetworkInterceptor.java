package com.skt.doss.portal.front.common.interceptor;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import com.skt.doss.portal.front.common.constant.Network;
import com.skt.doss.portal.front.common.util.ServletUtil;

@Component
public class ConnectNetworkInterceptor implements HandlerInterceptor {
  
  private static final Logger log = LoggerFactory.getLogger(ConnectNetworkInterceptor.class);

  @Value("${doss.client.network.prefix.SKT}")
  private String[] networkPrefixSKT;
  
  @Value("${doss.client.network.prefix.VDI}")
  private String[] networkPrefixVDI;
  
  @Value("${doss.client.network.prefix.SOC}")
  private String[] networkPrefixSOC;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
    HttpSession session = request.getSession();
    
    log.debug("!@# networkPrefixSKT: {}", Arrays.toString(networkPrefixSKT));
    log.debug("!@# networkPrefixVDI: {}", Arrays.toString(networkPrefixVDI));
    log.debug("!@# networkPrefixSOC: {}", Arrays.toString(networkPrefixSOC));

    String clientIp = ServletUtil.getClientIp(request);
    log.debug("!@# CLIENT_IP: {}", clientIp);
    if (!StringUtils.hasText(clientIp)) {
      log.error("!@# NOT FOUND CLIENT_IP.");
      return true;
    }
        
    for (String prefix : networkPrefixSKT) {
      if (clientIp.startsWith(prefix)) {
        session.setAttribute(Network.CLIENT_NETWORK_ATTRIBUTE, Network.SKT);
        return true;
      }
    }
    
    for (String prefix : networkPrefixVDI) {
      if (clientIp.startsWith(prefix)) {
        session.setAttribute(Network.CLIENT_NETWORK_ATTRIBUTE, Network.VDI);
        return true;
      }
    }
    
    for (String prefix : networkPrefixSOC) {
      if (clientIp.startsWith(prefix)) {
        session.setAttribute(Network.CLIENT_NETWORK_ATTRIBUTE, Network.SOC);
        return true;
      }
    }
    
    session.setAttribute(Network.CLIENT_NETWORK_ATTRIBUTE, Network.EXT);
    
    return true;
  }
}
