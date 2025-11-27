package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> listAllProjects() {
        return ResponseEntity.ok(ResponseWrapper.builder()
                .message("Projects are successfully retrieved")
                .data(projectService.listAllProjects())
                .code(HttpStatus.OK.value())
                .success(true)
                .build());
    }


    @GetMapping("/{projectCode}")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("projectCode") String projectCode) {

        return ResponseEntity.ok(ResponseWrapper.builder()
                .message("Related project retrieved")
                .data(projectService.getByProjectCode(projectCode))
                .code(HttpStatus.OK.value())
                .success(true)
                .build());
    }


    //create-update
    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createProjectByBody(@RequestBody ProjectDTO projectDTO) {

        projectService.save(projectDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseWrapper.builder()
                        .message("Project successfully created")
                        .data(projectDTO)
                        .success(true)
                        .code(HttpStatus.CREATED.value())
                        .build()
        );
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseWrapper> updateProjectByBody(@RequestBody ProjectDTO projectDTO) {

        projectService.update(projectDTO);

        return ResponseEntity.ok(ResponseWrapper.builder()
                .message("Project successfully updated")
                .data(projectDTO)
                .success(true)
                .code(HttpStatus.OK.value())
                .build());
    }


    @GetMapping("/delete/{projectCode}")
    public ResponseEntity<ResponseWrapper> deleteProjectByCode(@PathVariable("projectCode") String projectCode) {
        projectService.delete(projectCode);

        return ResponseEntity.ok(ResponseWrapper.builder()
                .message("Project deleted")
                .data(projectService.getByProjectCode(projectCode))
                .success(true)
                .code(HttpStatus.OK.value())
                .build());
    }

    @GetMapping("/{managerUsername}")
    public ResponseEntity<ResponseWrapper> getProjectByManagerUsername(@PathVariable("managerUsername") String managerUsername) {
        return ResponseEntity.ok(ResponseWrapper.builder()
                .message("Project retrieved")
                .data(projectService.listAllNonCompletedByAssignedManager(userService.findByUserName(managerUsername)))
                .success(true)
                .code(HttpStatus.OK.value())
                .build());
    }



}
