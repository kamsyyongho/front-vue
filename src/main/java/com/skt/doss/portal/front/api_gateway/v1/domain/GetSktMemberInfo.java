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
public class GetSktMemberInfo implements Serializable {
  
  @Getter
  @Setter
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Res extends CommonRes {
    private Result result;
  }
  
  @Getter
  @Setter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Result {
    private String deptNm;
    private String birthDt;
    private String empno;
    private String hname;
    private String ename;
    private String regno;
    private String indept;
    private String sosok;
    private String tsosok;
  }
}
