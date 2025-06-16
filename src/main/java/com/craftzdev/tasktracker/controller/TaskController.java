package com.craftzdev.tasktracker.controller;

import com.craftzdev.tasktracker.dto.TaskDto;
import com.craftzdev.tasktracker.service.TaskService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

    TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        TaskDto foundTask = taskService.findById(id);
        return ResponseEntity.ok(foundTask);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody @Valid TaskDto taskDto) {
        TaskDto createdTask = taskService.save(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id,
                                              @RequestBody @Valid TaskDto taskDto) {
        TaskDto updatedTask = taskService.update(id, taskDto);
        return ResponseEntity.ok(updatedTask);
    }

//    @PatchMapping("/{id}/status")
//    public ResponseEntity<TaskDto> assignStatus(@PathVariable Long id,
//                                                @RequestBody TaskStatus status) {
//        TaskDto updatedTask = taskService.updateStatus(id, status);
//        return ResponseEntity.ok(updatedTask);
//    }
//
//    @PatchMapping("/{id}/priority")
//    public ResponseEntity<TaskDto> assignPriority(@PathVariable Long id,
//                                                  @RequestBody TaskPriority priority) {
//        TaskDto updatedTask = taskService.updatePriority(id, priority);
//        return ResponseEntity.ok(updatedTask);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
