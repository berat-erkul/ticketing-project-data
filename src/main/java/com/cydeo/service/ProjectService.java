package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    ProjectDTO getByProjectCode(String projectCode);
    List<ProjectDTO> listAllProject();
    void save(ProjectDTO project);
    void update(ProjectDTO project);
    void delete(String projectCode);


    void complete(String projectCode);
}
