package com.skt.doss.portal.front.user.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skt.doss.portal.front.api_gateway.v1.domain.CheckDossUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.SktMemberYn;
import com.skt.doss.portal.front.user.service.UserService;

@Component
public class UserUtil {
  
  private static final Logger log = LoggerFactory.getLogger(UserUtil.class);

  @Autowired
  private UserService userService;
  
  public boolean checkDossUser(String dossId, String pwd) {
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


  public boolean checkSktMember(String empno) {
    SktMemberYn.Res resSktMemberYn = userService.sktMemberYn(empno);
    log.debug("!@# resSktMemberYn: {}", resSktMemberYn);
    if (resSktMemberYn != null && "Y".equals(resSktMemberYn.getSktMemberYn())) {
      return true;
    }
    return false;
  }
  
}
