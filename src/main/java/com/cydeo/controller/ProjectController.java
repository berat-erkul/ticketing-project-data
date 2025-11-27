package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@Tag(name = "Project Controller", description = "Project API")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @Operation(summary = "Get Projects")
    public ResponseEntity<ResponseWrapper> getProjects() {

        List<ProjectDTO> projectDTOList = projectService.listAllProjects();

        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectDTOList));
    }


    @GetMapping("/{code}")
    @Operation(summary = "Get Project by Code")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("code") String code) {

        ProjectDTO projectDTO = projectService.getByProjectCode(code);

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully retrieved", projectDTO));
    }

    @PostMapping
    @Operation(summary = "Create Project")
//    @RolesAllowed({"Admin", "Manager"})
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody @Valid ProjectDTO project) {

        projectService.save(project);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Project is successfully created", 201));
    }

    @PutMapping("/{projectCode}")
    @Operation(summary = "Update Project")
    public ResponseEntity<ResponseWrapper> updateProject(@PathVariable("projectCode") String projectCode, @RequestBody ProjectDTO project) {

        projectService.update(projectCode, project);

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully updated"));
    }


    @DeleteMapping("/{projectCode}")
    @Operation(summary = "Delete Project")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectCode") String projectCode) {

        projectService.delete(projectCode);

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully deleted"));
    }


    @GetMapping("/manager/project-status")
    @Operation(summary = "Get Projects By Manager")
    public ResponseEntity<ResponseWrapper> getProjectsByManager() {

        List<ProjectDTO> projectDTOList = projectService.listAllProjectDetails();

        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectDTOList));

    }

    @PutMapping("/manager/complete/{projectCode}")
    @Operation(summary = "Manager Complete Project")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode") String projectCode) {

        projectService.complete(projectCode);

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully completed"));

    }

}
