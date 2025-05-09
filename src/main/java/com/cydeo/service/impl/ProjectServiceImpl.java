package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.enums.Status;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.ProjectService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MapperUtil mapperUtil;

    public ProjectServiceImpl(ProjectRepository projectRepository, MapperUtil mapperUtil) {
        this.projectRepository = projectRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public ProjectDTO getByProjectCode(String projectCode) {

        Project project = projectRepository.findByProjectCode(projectCode);
        return mapperUtil.convert(project, ProjectDTO.class);
    }

    @Override
    public List<ProjectDTO> listAllProject() {
        // Sort by projectCode
        List<Project> projectList = projectRepository.findAll(Sort.by("projectCode"));
        return projectList.stream().map(project -> mapperUtil.convert(project,ProjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO project) {
        project.setProjectStatus(Status.OPEN);
        projectRepository.save(mapperUtil.convert(project,Project.class));
    }

    @Override
    public void delete(String projectCode) {

        Project project = projectRepository.findByProjectCode(projectCode);
        project.setIsDeleted(true);

        //(optional)project code is unique, so I want to make this project code reusable
        project.setProjectCode(project.getProjectCode() + "-" + project.getId());

        projectRepository.save(project);
    }


    @Override
    public void update(ProjectDTO project) {
        Project foundProject = projectRepository.findByProjectCode(project.getProjectCode());
        Project convertedProject = mapperUtil.convert(project, Project.class);
        convertedProject.setId(foundProject.getId());
        projectRepository.save(convertedProject);
    }

    @Override
    public void complete(String projectCode) {

        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);

        projectRepository.save(project);
    }
}
