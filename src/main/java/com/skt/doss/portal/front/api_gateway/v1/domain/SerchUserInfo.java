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
public class SerchUserInfo implements Serializable {
  
  @Getter
  @Setter
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class res extends CommonRes {
	  private String dossId;
	  private String userType;
	  private String userTypeNm;
	  private String companyCd;
	  private String companyNm;
	  private String portalRoleId;
	  private String deptNm;
	  private String userNm;
  }
}
