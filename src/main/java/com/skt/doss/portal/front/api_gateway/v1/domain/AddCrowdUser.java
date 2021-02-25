package com.skt.doss.portal.front.api_gateway.v1.domain;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AddCrowdUser implements Serializable {
  
  @Getter
  @Setter
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Req {
    
    private String name;
    
    private Password password;
    
    private Boolean active;
    
    @JsonProperty("first-name")
    private String firstName;
    
    @JsonProperty("last-name")
    private String lastName;
    
    @JsonProperty("display-name")
    private String displayName;
    
    private String email;
  }
  
  @Getter
  @Setter
  @Builder
  @ToString
  public static class Password {
    private String value;
  }
  
  @Getter
  @Setter
  @SuperBuilder
  @NoArgsConstructor
//  @AllArgsConstructor
  @ToString
  public static class Res extends CommonRes {
    
//    private String result;
  }
  
  
  
//  {
//    "name":"test1000",
//    "password":{
//       "value":"1234"
//    },
//    "active":true,
//    "first-name":"test1000",
//    "last-name":"test1000",
//    "display-name":"test1000",
//    "email":"sample@user.cool"
// }
  
  
  
  
  
}
