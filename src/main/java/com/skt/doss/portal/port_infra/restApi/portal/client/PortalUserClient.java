package com.skt.doss.portal.port_infra.restApi.portal.client;

import com.skt.doss.common.config.ApiHeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "admin-svc-api", url = "${feign.doss-portal-api.url}", configuration = {ApiHeaderConfiguration.class})
public interface PortalUserClient {

    @GetMapping(value="/user/dossMemberYn")
    public ResponseEntity<Object> dossMemberYn(@RequestParam Map<String,Object> param);

}

