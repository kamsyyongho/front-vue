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
public class CrowdUserYn implements Serializable {
  
  @Getter
  @Setter
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Req {
    private String userId;    
//    private String key;
  }
  
  @Getter
  @Setter
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Res extends CommonRes {
    private String userYn;
  }
  
  
  
  
  
}
