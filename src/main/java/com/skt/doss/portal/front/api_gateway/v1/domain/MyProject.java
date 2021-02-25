package com.skt.doss.portal.front.api_gateway.v1.domain;

import java.util.List;

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
public class MyProject {

	@Getter
	@Setter
	@SuperBuilder
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	public static class Req {
	  private String dossId;    
	}
	
	@Getter
	@Setter
	@SuperBuilder
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	public static class Res extends CommonRes {
	  private List<Object> result;
	}
	
}
