package com.skt.doss.portal.front.common.service;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.skt.doss.portal.front.api_gateway.v1.client.PortalBackendApiClient;
import com.skt.doss.portal.front.api_gateway.v1.domain.SearchCompanyInfo;
import com.skt.doss.portal.front.api_gateway.v1.domain.SearchCompanyList;
import com.skt.doss.portal.front.common.domain.Hello;

@Service
public class CommonService {
    
  private static final Logger log = LoggerFactory.getLogger(CommonService.class);

  @Value("${spring.application.name}")
  private String project;
  
  @Autowired
  private Environment env;
  
  @Autowired
  private PortalBackendApiClient portalBackendApiClient;

  public Hello hello(String name) throws Exception {

    return Hello.builder()
        .project(project)
        .profile(env.getActiveProfiles()[0])
        .serverTime(LocalDateTime.now().toString())
        .helloMessage("Hello! " + name)
        .build();
  }
  
  public SearchCompanyList.Res searchCompanyList() throws Exception {

    ResponseEntity<SearchCompanyList.Res> responseEntity = portalBackendApiClient.searchCompanyList();

    if (responseEntity != null) {
      log.debug("!@# SearchCompanyList.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        SearchCompanyList.Res res = responseEntity.getBody();
        log.debug("!@# SearchCompanyList.Res: {}", res.toString());
        return res;
      }
    }

    return null;
  }
  
  public SearchCompanyInfo.Res searchCompanyInfo(String companyName) throws Exception {

    ResponseEntity<SearchCompanyInfo.Res> responseEntity = portalBackendApiClient.searchCompanyInfo(companyName);

    if (responseEntity != null) {
      log.debug("!@# SearchCompanyInfo.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        SearchCompanyInfo.Res res = responseEntity.getBody();
        log.debug("!@# SearchCompanyInfo.Res: {}", res.toString());
        return res;
      }
    }

    return null;
  }

}
