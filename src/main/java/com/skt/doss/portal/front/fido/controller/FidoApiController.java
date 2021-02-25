package com.skt.doss.portal.front.fido.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skt.doss.portal.front.api_gateway.v1.domain.Fido;
import com.skt.doss.portal.front.api_gateway.v1.domain.SignUp;
import com.skt.doss.portal.front.fido.service.FidoService;

import io.swagger.annotations.ApiOperation;

@RestController
public class FidoApiController {
  
  @Autowired
  private FidoService fidoService;

  @GetMapping("/api/fido/isRegPushInfo/{dossId}")
  public ResponseEntity<Map> isRegPushInfo(@PathVariable String dossId) throws Exception {
	  Map resultMap = new HashMap();
	  System.out.println(dossId);
	  resultMap.put("result", fidoService.isRegPushInfo(dossId));
    return ResponseEntity.ok(resultMap);
  }
  
  @ApiOperation(value = "fido �솢�꽦�솕 肄붾뱶 �슂泥�", httpMethod = "POST", notes = "fido �솢�꽦�솕 肄붾뱶 �슂泥�")
  @PostMapping(value="/api/fido/getActiveCode")
  public ResponseEntity<Map> getActiveCode(@RequestBody Fido.req req) throws Exception {
	  Map resultMap = new HashMap();
	  System.out.println(req.getDossId());
	  //System.out.println(dossId);
	  resultMap.put("result", fidoService.getActiveCode(req));
    return ResponseEntity.ok(resultMap);
  }
  
  @ApiOperation(value = "fido �솢�꽦�솕�벑濡� ��湲�", httpMethod = "POST", notes = "")
  @PostMapping(value="/api/fido/waitMobileUse")
  public ResponseEntity<Map> waitMobileUse(@RequestBody Fido.req req) throws Exception {
	  //System.out.println(req.getDossId() + " : waitMobileUse getDossId ");
	  Map resultMap = new HashMap();
	  
	  //System.out.println(dossId);
	  resultMap.put("result", fidoService.waitMobileUse(req));
    return ResponseEntity.ok(resultMap);
  }
  
  
  @ApiOperation(value = "fido registration ", httpMethod = "GET", notes = "")
  @GetMapping(value="/api/fido/registration/{dossId}")
  public ResponseEntity<Map> registrationRequestFrom(@PathVariable String dossId) throws Exception {
	  Map resultMap = new HashMap();
	  //System.out.println(req.getRevSession());
	  //System.out.println(dossId);
	  resultMap.put("result", fidoService.registrationRequestFrom(dossId));
    return ResponseEntity.ok(resultMap);
  }
}
