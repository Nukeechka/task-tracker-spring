package com.craftzdev.tasktracker.service;

import com.craftzdev.tasktracker.dto.TaskDto;
import com.craftzdev.tasktracker.entity.Task;
import com.craftzdev.tasktracker.mapper.TaskMapper;
import com.craftzdev.tasktracker.repository.TaskRepository;
import com.craftzdev.tasktracker.util.exceptions.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    TaskMapper taskMapper;


    @Override
    public TaskDto findById(Long id) {
        log.info("Fetching task with ID '{}'", id);
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not" +
                " " + "found with ID: " + id));
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskDto> findAll() {
        log.info("Fetching all tasks");
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::toDto).toList();
    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);

        Task savedTask = taskRepository.save(task);
        log.info("Task '{}' has been successfully created with ID '{}'", savedTask.getTitle(), savedTask.getId());
        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskDto update(Long id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Task not found with ID" + id));

        taskMapper.updateTaskFromDto(taskDto, existingTask);

        Task updatedTask = taskRepository.save(existingTask);
        log.info("Task '{}' has been successfully updated with ID '{}'", updatedTask.getTitle(),
                updatedTask.getId());
        return taskMapper.toDto(updatedTask);
    }

//    @Override
//    public TaskDto updateStatus(Long id, TaskStatus status) {
//        Task existingTask = taskRepository.findById(id).orElseThrow(() ->
//                new NotFoundException("Task not found with ID" + id));
//
//        existingTask.setStatus(status);
//
//        Task updatedTask = taskRepository.save(existingTask);
//
//        log.info("Task title updated with ID '{}', new status='{}'", id, status);
//        return taskMapper.toDto(updatedTask);
//    }
//
//    @Override
//    public TaskDto updatePriority(Long id, TaskPriority priority) {
//        Task existingTask = taskRepository.findById(id).orElseThrow(() ->
//                new NotFoundException("Task not found with ID" + id));
//
//        existingTask.setPriority(priority);
//
//        Task updatedTask = taskRepository.save(existingTask);
//
//        log.info("Task title updated with ID '{}', new priority='{}'", id, priority);
//        return taskMapper.toDto(updatedTask);
//    }

    @Override
    public void delete(Long id) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Task not found with ID " + id));
        taskRepository.deleteById(existingTask.getId());
        log.info("Task with ID '{}' has been deleted", id);
    }
}
