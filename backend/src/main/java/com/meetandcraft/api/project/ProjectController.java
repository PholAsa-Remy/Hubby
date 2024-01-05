package com.meetandcraft.api.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project")
    public ResponseEntity<ProjectResponse> getAllProject (
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        ProjectResponse projects = projectService.getAllProject(pageNo,pageSize);
        return new ResponseEntity<> (projects, HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById (
            @PathVariable(value = "projectId")UUID projectId
    ){
        return new ResponseEntity<>(projectService.getProjectById(projectId),HttpStatus.OK);
    }

    @PostMapping("/project")
    public  ResponseEntity<ProjectDto> createProject (
            @RequestBody ProjectDto projectDto) {
        return new ResponseEntity<>(projectService.createProject(projectDto),HttpStatus.CREATED);
    }

    @PutMapping("/project/{projectId}")
    public ResponseEntity<ProjectDto> updateProjectById (
            @PathVariable(value = "projectId")UUID projectId,
            @RequestBody ProjectDto projectDto
    ){
        return new ResponseEntity<>(projectService.updateProjectById(projectId,projectDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<String> deleteProjectById (
            @PathVariable(value = "projectId")UUID projectId
    ){
        projectService.deleteProjectById(projectId);
        return new ResponseEntity<>("Successfully deleted the project " + projectId,HttpStatus.OK);
    }
}
