package com.skt.doss.portal.front.api_gateway.v1.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString()
public class Fido  {
	
	  @Getter
	  @Setter
	  @SuperBuilder
	  @NoArgsConstructor
	  @AllArgsConstructor
	  @ToString
	  public static class req {
		
      private String revSession;
      private String activecode;
      private String code;
      private String msg;
      private String dossId;
      private String phoneNo;
     
	 } 
	  
	  
	  @Getter
	  @Setter
	  @SuperBuilder
	  @NoArgsConstructor
	  @AllArgsConstructor
	  @ToString
	  public static class res {
		
		  private String revSession;
		  private String activecode;
		  private String code;
		  private String msg;
		  private String dossId;
		  private String phoneNo;
     
	 } 
}
