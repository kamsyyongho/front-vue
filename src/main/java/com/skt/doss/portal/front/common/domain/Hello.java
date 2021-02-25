package com.skt.doss.portal.front.common.domain;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
@SuppressWarnings("serial")
public class Hello implements Serializable {
  private String project;
  private String profile;
  private String serverTime;
  private String helloMessage;
}


