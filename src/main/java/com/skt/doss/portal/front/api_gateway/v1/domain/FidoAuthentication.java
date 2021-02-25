package com.skt.doss.portal.front.api_gateway.v1.domain;

import java.io.Serializable;
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
public class FidoAuthentication implements Serializable {
  
  @Getter
  @Setter
  @SuperBuilder
  @NoArgsConstructor
  @ToString
  public static class Res extends CommonRes {}
}
