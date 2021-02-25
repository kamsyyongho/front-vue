package com.skt.doss.portal.controller.web.user;

import com.skt.doss.portal.port_infra.restApi.portal.client.PortalUserClient;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {
    private final PortalUserClient portalUserClient;

    @PostMapping("/user")
    @ApiOperation(value = "유저정보 조회", httpMethod = "POST", notes = "유저 정보 조회 API.")
    public ResponseEntity<Object> getTestUser(@RequestBody Map<String,Object> param){
        return portalUserClient.dossMemberYn(param);
    }
}
