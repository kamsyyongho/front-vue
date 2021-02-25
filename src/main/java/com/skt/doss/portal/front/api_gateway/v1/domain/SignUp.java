package com.skt.doss.portal.front.api_gateway.v1.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@Builder
@SuppressWarnings("serial")
public class SignUp implements Serializable {
  
  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Req {
    private String ci;
    private String dossId;
    private String userType;
    private String sktEmpNo;
    private String phoneNo;
    private String email;
    private String companyCd;
    private String companyNm;
    private String pwd;
    private String birthDt;
    private String deptNm;
    private String userNm;
  }
  
  @Getter
  @Setter
  @SuperBuilder
  @AllArgsConstructor
  @ToString(callSuper = true)
  public static class Res extends CommonRes {}
  
}
