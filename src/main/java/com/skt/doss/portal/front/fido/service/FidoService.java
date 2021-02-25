package com.skt.doss.portal.front.fido.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skt.doss.portal.front.api_gateway.v1.client.PortalBackendApiClient;
import com.skt.doss.portal.front.api_gateway.v1.domain.Fido;
import com.skt.doss.portal.front.api_gateway.v1.domain.SignUp;

@Service
public class FidoService {

  private static final Logger log = LoggerFactory.getLogger(FidoService.class);

  @Autowired
  private PortalBackendApiClient portalBackendApiClient;

  public Fido.res isRegPushInfo(String dossId) {
    
    log.debug("!@# SignUp.Req: {}", dossId);

    ResponseEntity<Fido.res> responseEntity = portalBackendApiClient.isRegPushInfo(dossId);

    if (responseEntity != null) {
      log.debug("!@# ResponseEntity<SignUp.Res>: {}", responseEntity.toString());
      if(responseEntity.hasBody()) {
        Fido.res res = responseEntity.getBody();
        log.debug("!@# SignUp.Res: {}", res.toString());
        return res;
      }
    }

    return null;
  }
  
  public Fido.res getActiveCode(Fido.req req) {
	    
	    log.debug("!@# SignUp.Req: {}", req);

	    ResponseEntity<Fido.res> responseEntity = portalBackendApiClient.getActiveCode(req);

	    if (responseEntity != null) {
	      log.debug("!@# ResponseEntity<SignUp.Res>: {}", responseEntity.toString());
	      if(responseEntity.hasBody()) {
	        Fido.res res = responseEntity.getBody();
	        log.debug("!@# SignUp.Res: {}", res.toString());
	        return res;
	      }
	    }

	    return null;
	  }
  
  public Fido.res waitMobileUse(Fido.req req) {
	    
	    //System.out.println(req.getDossId() + " : dossId ............ ");
	  
	    

	    ResponseEntity<Fido.res> responseEntity = portalBackendApiClient.waitMobileUse(req);
	    System.out.println(" == ????? == " + req.toString());
	    if (responseEntity != null) {
	      System.out.println(responseEntity);
	      if(responseEntity.hasBody()) {
	        Fido.res res = responseEntity.getBody();
	        System.out.println(res.toString() + " == waitMobileUse hasBody == ");
	        return res;
	      }
	    }

	    return null;
	  }
  
  public Fido.res registrationRequestFrom(String fidoId) {
	    
	    log.debug("!@# SignUp.Req: {}", fidoId);
	    System.out.println(fidoId);

	    ResponseEntity<Fido.res> responseEntity = portalBackendApiClient.registrationRequestFrom(fidoId);

	    if (responseEntity != null) {
	      log.debug("!@# ResponseEntity<SignUp.Res>: {}", responseEntity.toString());
	      if(responseEntity.hasBody()) {
	        Fido.res res = responseEntity.getBody();
	        log.debug("!@# SignUp.Res: {}", res.toString());
	        return res;
	      }
	    }

	    return null;
	  }
  
}
