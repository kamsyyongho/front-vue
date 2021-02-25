package com.skt.doss.portal.front.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.skt.doss.portal.front.api_gateway.v1.domain.MyProject;
import com.skt.doss.portal.front.api_gateway.v1.domain.ProjectUser;
import com.skt.doss.portal.front.project.service.ProjectService;

@RestController
public class ProjectApiController {

	private static final Logger log = LoggerFactory.getLogger(ProjectApiController.class);
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/api/project/my-project")
	public ResponseEntity<MyProject.Res> getMyProject(Authentication authentication) {
		String dossId = (String) authentication.getPrincipal();
		return ResponseEntity.ok(projectService.getMyProject(dossId));
	}
	
	@GetMapping("/api/project/project-user/{projectId}")
	public ResponseEntity<ProjectUser.Res> selectProjectUserList(@PathVariable String projectId) {
		return ResponseEntity.ok(projectService.selectProjectUserList(projectId));
	}
	
}
