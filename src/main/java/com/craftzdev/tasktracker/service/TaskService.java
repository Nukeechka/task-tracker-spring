package com.craftzdev.tasktracker.service;

import com.craftzdev.tasktracker.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto findById(Long id);

    List<TaskDto> findAll();

    TaskDto save(TaskDto taskDto);

    TaskDto update(Long id, TaskDto taskDto);


//    TaskDto updateStatus(Long id, TaskStatus status);

//    TaskDto updatePriority(Long id, TaskPriority priority);

    void delete(Long id);
}
