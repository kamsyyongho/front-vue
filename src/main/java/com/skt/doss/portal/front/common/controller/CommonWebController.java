package com.skt.doss.portal.front.common.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonWebController implements ErrorController {  

  @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
  public String index() {
    return "index.html";
  }
  
//  @GetMapping("/error")
//  public String redirectRoot() {
//    return "index.html";
//  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
