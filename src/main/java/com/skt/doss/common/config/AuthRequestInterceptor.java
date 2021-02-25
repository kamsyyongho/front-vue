package com.skt.doss.common.config;

import com.skt.doss.common.utils.CipherManager;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class AuthRequestInterceptor implements RequestInterceptor {

	private static final String token = "doss-portal-front";

	@Override
	public void apply(RequestTemplate template) {
		// TODO Auto-generated method stub
		String dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		CipherManager cm = CipherManager.getInstance();
		String authToken = "";
		try {
			authToken = cm.aescEncrypt(token+"||" + dt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("암호화 오류 발생");
			authToken = "error";
		}
		template.header("API-AUTH-TOKEN", token);
	}

}
