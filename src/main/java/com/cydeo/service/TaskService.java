package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO, Long>{
    List<TaskDTO> findAllTasksByStatusIsNot(Status status);

    List<TaskDTO> findAllTasksByStatus(Status status);

    void updateStatus(TaskDTO task);
}
