package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@Tag(name = "Task Controller", description = "Task API")
public class TaskController {


    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Get Tasks")
    public ResponseEntity<ResponseWrapper> getTasks() {

        List<TaskDTO> taskDTOList = taskService.listAllTasks();

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskDTOList));

    }

    @GetMapping("/{taskCode}")
    @Operation(summary = "Get Task By Code")
    public ResponseEntity<ResponseWrapper> getTaskByTaskCode(@PathVariable("taskCode") String taskCode) {

        TaskDTO taskDTO = taskService.findByTaskCode(taskCode);

        return ResponseEntity.ok(new ResponseWrapper("Task is successfully retrieved", taskDTO));

    }

    @PostMapping
    @Operation(summary = "Create Task")
    public ResponseEntity<ResponseWrapper> createTask(@RequestBody @Valid TaskDTO task) {

        taskService.save(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Task is successfully created", 201));
    }

    @PutMapping("/{taskCode}")
    @Operation(summary = "Update Task")
    public ResponseEntity<ResponseWrapper> updateTask(@PathVariable("taskCode") String taskCode, @RequestBody TaskDTO task) {

        taskService.update(taskCode, task);

        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated"));

    }

    @DeleteMapping("/{taskCode}")
    @Operation(summary = "Delete Task")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable("taskCode") String taskCode) {

        taskService.delete(taskCode);

        return ResponseEntity.ok(new ResponseWrapper("Task is successfully deleted"));
    }

    @GetMapping("/employee/pending-tasks")
    @Operation(summary = "Get Pending Tasks")
    public ResponseEntity<ResponseWrapper> employeePendingTasks() {

        List<TaskDTO> taskDTOList = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskDTOList));
    }

    @GetMapping("/employee/archive")
    @Operation(summary = "Get Archived Tasks")
    public ResponseEntity<ResponseWrapper> employeeArchivedTasks() {

        List<TaskDTO> taskDTOList = taskService.listAllTasksByStatus(Status.COMPLETE);

        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskDTOList));
    }


    @PutMapping("/employee/update/{taskCode}")
    @Operation(summary = "Employee Update Task")
    public ResponseEntity<ResponseWrapper> employeeUpdateTask(@PathVariable("taskCode") String taskCode, @RequestBody TaskDTO task) {

        taskService.update(taskCode, task);

        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated"));

    }

}
