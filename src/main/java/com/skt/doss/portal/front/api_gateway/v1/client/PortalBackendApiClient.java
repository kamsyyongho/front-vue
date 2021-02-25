package com.skt.doss.portal.front.api_gateway.v1.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.skt.doss.common.config.ApiHeaderConfiguration;
import com.skt.doss.portal.front.api_gateway.v1.domain.AddCrowdUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.CertiAsisUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.CheckDossUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.CrowdUserToken;
import com.skt.doss.portal.front.api_gateway.v1.domain.CrowdUserYn;
import com.skt.doss.portal.front.api_gateway.v1.domain.DeleteCrowdUserToken;
import com.skt.doss.portal.front.api_gateway.v1.domain.Fido;
import com.skt.doss.portal.front.api_gateway.v1.domain.FidoAuthentication;
import com.skt.doss.portal.front.api_gateway.v1.domain.GetSktMemberInfo;
import com.skt.doss.portal.front.api_gateway.v1.domain.LdapCheckDossUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.MakeDossId;
import com.skt.doss.portal.front.api_gateway.v1.domain.MyProject;
import com.skt.doss.portal.front.api_gateway.v1.domain.ProjectUser;
import com.skt.doss.portal.front.api_gateway.v1.domain.SearchCompanyInfo;
import com.skt.doss.portal.front.api_gateway.v1.domain.SearchCompanyList;
import com.skt.doss.portal.front.api_gateway.v1.domain.SerchUserInfo;
import com.skt.doss.portal.front.api_gateway.v1.domain.SignUp;
import com.skt.doss.portal.front.api_gateway.v1.domain.SktMemberYn;

@FeignClient(name = "admin-svc-api", url = "${feign.doss-portal-api.url}", configuration = {ApiHeaderConfiguration.class})
public interface PortalBackendApiClient {

    @PostMapping(value="/api/v1/user/checkDossUser")
    public ResponseEntity<CheckDossUser.Res> checkDossUser(@RequestBody CheckDossUser.Req req);
    
    @GetMapping(value="/api/v1/comm/searchCompanyList")
    public ResponseEntity<SearchCompanyList.Res> searchCompanyList();
    
    @GetMapping(value="/api/v1/comm/searchCompanyInfo/{companyName}")
    public ResponseEntity<SearchCompanyInfo.Res> searchCompanyInfo(@PathVariable String companyName);
    
    @PostMapping(value="/api/v1/user/signUp")
    public ResponseEntity<SignUp.Res> signUp(@RequestBody SignUp.Req req);
    
    @GetMapping(value="/api/v1/user/makeDossId")
    public ResponseEntity<MakeDossId.Res> makeDossId();

    @GetMapping(value="/api/v1/user/sktMemberYn/{empno}")
    public ResponseEntity<SktMemberYn.Res> sktMemberYn(@PathVariable String empno);
    
    @GetMapping(value="/api/v1/fido/fidoAuthentication/{dossId}")
    public ResponseEntity<FidoAuthentication.Res> fidoAuthentication(@PathVariable String dossId);

    @GetMapping(value="/api/v1/fido/isRegPushInfo/{dossId}")
    public ResponseEntity<Fido.res> isRegPushInfo(@PathVariable  String dossId);  
    
    @PostMapping(value="/api/v1/fido/getActiveCode")
    public ResponseEntity<Fido.res> getActiveCode(@RequestBody Fido.req req);  
    
    @PostMapping(value="/api/v1/fido/waitMobileUse")
    public ResponseEntity<Fido.res> waitMobileUse(@RequestBody Fido.req req);  
    
    @GetMapping(value="/api/v1/fido/registrationRequestFrom/{dossId}")
    public ResponseEntity<Fido.res> registrationRequestFrom(@PathVariable String dossId);
    
    @GetMapping(value="/api/v1/crowd/user-yn")
    public ResponseEntity<CrowdUserYn.Res> crowdUserYn(@RequestParam String userId);
    
    @PostMapping(value="/api/v1/crowd/user-token")
    public ResponseEntity<CrowdUserToken.Res> crowdUserToken(@RequestBody CrowdUserToken.Req req);
    
    @DeleteMapping(value="/api/v1/crowd/delete-user-token/{token}")
    public ResponseEntity<DeleteCrowdUserToken.Res> deleteCrowdUserToken(@PathVariable String token);
    
    @PostMapping(value="/api/v1/user/certiAsisUser")
    public ResponseEntity<CertiAsisUser.Res> certiAsisUser(@RequestBody CertiAsisUser.Req req);
    
    @PostMapping(value="/api/v1/crowd/add-crowd-user")
    public ResponseEntity<AddCrowdUser.Res> addCrowdUser(@RequestBody AddCrowdUser.Req req);
    
    @GetMapping(value="/api/v1/user/getSktMemberInfo/{empno}")
    public ResponseEntity<GetSktMemberInfo.Res> getSktMemberInfo(@PathVariable String empno);
    
    @GetMapping(value="/api/v1/project/my-project/{dossId}")
    public ResponseEntity<MyProject.Res> getMyProject(@PathVariable String dossId);
    
    @GetMapping(value="/api/v1/project/project-user/{projectId}")
    public ResponseEntity<ProjectUser.Res> selectProjectUserList(@PathVariable String projectId);
    
    @GetMapping(value="/api/v1/user/searchUserInfo/{dossId}")
    public ResponseEntity<SerchUserInfo.res> searchUserInfo(@PathVariable String dossId);

    @PostMapping(value="/api/v1/ldap/checkDossUser/{dossId}")
    public ResponseEntity<LdapCheckDossUser.Res> ldapCheckDossUser(@RequestBody LdapCheckDossUser.Req req);
}

