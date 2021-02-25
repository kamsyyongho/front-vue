package com.skt.doss.portal.front.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skt.doss.portal.front.api_gateway.v1.client.PortalBackendApiClient;
import com.skt.doss.portal.front.api_gateway.v1.domain.AddCrowdUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.CertiAsisUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.CheckDossUser;
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

@Service
public class UserService {

  private static final Logger log = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private PortalBackendApiClient portalBackendApiClient;

  public SignUp.Res signUp(SignUp.Req req) {    
    log.debug("!@# SignUp.Req: {}", req.toString());
    ResponseEntity<SignUp.Res> responseEntity = portalBackendApiClient.signUp(req);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<SignUp.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        SignUp.Res res = responseEntity.getBody();
        log.debug("!@# SignUp.Res: {}", res.toString());

        // Call AddCrowdUser API
//        if ("OK".equals(res.getCode())) {
//          AddCrowdUser.Req reqAddCrowdUser = AddCrowdUser.Req.builder()
//              .name(req.getDossId())
//              .password(AddCrowdUser.Password.builder().value(req.getPwd()).build())
//              .active(true)
//              .firstName(req.getUserNm())
//              .lastName("")
//              .displayName(req.getUserNm())
//              .email(req.getEmail())
//              .build();
//          ResponseEntity<AddCrowdUser.Res> responseEntityAddCrowdUser = portalBackendApiClient.addCrowdUser(reqAddCrowdUser);
//          log.debug("!@# responseEntityAddCrowdUser: {}", responseEntityAddCrowdUser.toString());
//          if (responseEntityAddCrowdUser != null && responseEntityAddCrowdUser.hasBody()) {
//            AddCrowdUser.Res resAddCrowdUser = responseEntityAddCrowdUser.getBody();
//            log.debug("!@# AddCrowdUser.Res: {}", resAddCrowdUser.toString());
//          }
//        }

        return res;
      }
    }
    return null;
  }


  public MakeDossId.Res makeDossId() {
    ResponseEntity<MakeDossId.Res> responseEntity = portalBackendApiClient.makeDossId();
    if (responseEntity != null && responseEntity.hasBody()) {
      log.debug("!@# ResponseEntity<MakeDossId.Res>: {}", responseEntity.toString());
      MakeDossId.Res res = responseEntity.getBody();
      log.debug("!@# MakeDossId.Res: {}", res.toString());
      return res;
    }
    return null;
  }


  public CheckDossUser.Res checkDossUser(CheckDossUser.Req req) {
    ResponseEntity<CheckDossUser.Res> responseEntity = portalBackendApiClient.checkDossUser(
        CheckDossUser.Req.builder()
        .dossId(req.getDossId())
        .pwd(req.getPwd())
        .build());
    log.debug("!@# ResponseEntity<CheckDossUser.Res>: {}", responseEntity.toString());
    if (responseEntity != null && responseEntity.hasBody()) {
      CheckDossUser.Res res = responseEntity.getBody();
      log.debug("!@# CheckDossUser.Res: {}", res.toString());
      return res;
    }
    return null;
  }


  public SktMemberYn.Res sktMemberYn(String empno) {
    ResponseEntity<SktMemberYn.Res> responseEntity = portalBackendApiClient.sktMemberYn(empno);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<SktMemberYn.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        SktMemberYn.Res res = responseEntity.getBody();
        log.debug("!@# SktMemberYn.Res: {}", res.toString());
        return res;
      }
    }
    return null;
  }


  public FidoAuthentication.Res fidoAuthentication(String dossId) {
    ResponseEntity<FidoAuthentication.Res> responseEntity = portalBackendApiClient.fidoAuthentication(dossId);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<FidoAuthentication.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        FidoAuthentication.Res res = responseEntity.getBody();
        log.debug("!@# FidoAuthentication.Res: {}", res.toString());
        return res;
      }
    }
    return null;
  }


  public CrowdUserYn.Res crowdUserYn(String userId) {
    ResponseEntity<CrowdUserYn.Res> responseEntity = portalBackendApiClient.crowdUserYn(userId);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<CrowdUserYn.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        CrowdUserYn.Res res = responseEntity.getBody();
        log.debug("!@# CrowdUserYn.Res: {}", res.toString());
        return res;
      }
    }
    return null;
  }


  public CrowdUserToken.Res crowdUserToken(CrowdUserToken.Req req) {
    ResponseEntity<CrowdUserToken.Res> responseEntity = portalBackendApiClient.crowdUserToken(req);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<CrowdUserToken.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        CrowdUserToken.Res res = responseEntity.getBody();
        log.debug("!@# CrowdUserToken.Res: {}", res.toString());
        return res;
      }
    }
    return null;
  }
  
  public DeleteCrowdUserToken.Res deleteCrowdUserToken(String token) {
    ResponseEntity<DeleteCrowdUserToken.Res> responseEntity = portalBackendApiClient.deleteCrowdUserToken(token);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<DeleteCrowdUserToken.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
    	  DeleteCrowdUserToken.Res res = responseEntity.getBody();
        log.debug("!@# DeleteCrowdUserToken.Res: {}", res.toString());
        return res;
      }
    }
    return null;
  }


  public CertiAsisUser.Res certiAsisUser(CertiAsisUser.Req req) {
    ResponseEntity<CertiAsisUser.Res> responseEntity = portalBackendApiClient.certiAsisUser(req);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<CertiAsisUser.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        CertiAsisUser.Res res = responseEntity.getBody();
        log.debug("!@# CertiAsisUser.Res: {}", res.toString());
        return res;
      }
    }
    return null;
  }


  public AddCrowdUser.Res addCrowdUser(AddCrowdUser.Req req) {
    ResponseEntity<AddCrowdUser.Res> responseEntity = portalBackendApiClient.addCrowdUser(req);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<AddCrowdUser.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        AddCrowdUser.Res res = responseEntity.getBody();
        log.debug("!@# AddCrowdUser.Res: {}", res.toString());
        return res;
      }
    }
    return null;
  }


  public GetSktMemberInfo.Res getSktMemberInfo(String empno) {
    ResponseEntity<GetSktMemberInfo.Res> responseEntity = portalBackendApiClient.getSktMemberInfo(empno);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<GetSktMemberInfo.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        GetSktMemberInfo.Res res = responseEntity.getBody();
        log.debug("!@# GetSktMemberInfo.Res: {}", res.toString());
        return res;
      }
    }
    return null;
  }


  public SerchUserInfo.res searchUserInfo(String dossId) {
    ResponseEntity<SerchUserInfo.res> responseEntity = portalBackendApiClient.searchUserInfo(dossId);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<SerchUserInfo.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        SerchUserInfo.res res = responseEntity.getBody();
        log.debug("!@# GetSktMemberInfo.Res: {}", res.toString());
        return res;
      }
    }
    return null;
  }


  public LdapCheckDossUser.Res ldapCheckDossUser(LdapCheckDossUser.Req req) {
    ResponseEntity<LdapCheckDossUser.Res> responseEntity = portalBackendApiClient.ldapCheckDossUser(req);
    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<LdapCheckDossUser.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        LdapCheckDossUser.Res res = responseEntity.getBody();
        log.debug("!@# LdapCheckDossUser.Res: {}", res.toString());
        return res;
      }
    }
    return null;
  }
}
