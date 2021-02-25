package com.skt.doss.portal.front.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skt.doss.portal.front.api_gateway.v1.client.PortalBackendApiClient;
import com.skt.doss.portal.front.api_gateway.v1.domain.MyProject;
import com.skt.doss.portal.front.api_gateway.v1.domain.ProjectUser;
import com.skt.doss.portal.front.user.service.UserService;

@Service
public class ProjectService {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private PortalBackendApiClient portalBackendApiClient;
	
	public MyProject.Res getMyProject(String dossId) {
		ResponseEntity<MyProject.Res> responseEntity = portalBackendApiClient.getMyProject(dossId);
		log.debug("!@# ResponseEntity<MyProject.Res>: {}", responseEntity.toString());
	    if (responseEntity != null && responseEntity.hasBody()) {
	    	MyProject.Res res = responseEntity.getBody();
	      log.debug("!@# MyProject.Res: {}", res.toString());
	      return res;
	    }
	    return null;
	}
	
	public ProjectUser.Res selectProjectUserList(String projectId) {
		ResponseEntity<ProjectUser.Res> responseEntity = portalBackendApiClient.selectProjectUserList(projectId);
		log.debug("!@# ResponseEntity<ProjectUser.Res>: {}", responseEntity.toString());
	    if (responseEntity != null && responseEntity.hasBody()) {
	    	ProjectUser.Res res = responseEntity.getBody();
	      log.debug("!@# ProjectUser.Res: {}", res.toString());
	      return res;
	    }
	    return null;
	}
	
}
