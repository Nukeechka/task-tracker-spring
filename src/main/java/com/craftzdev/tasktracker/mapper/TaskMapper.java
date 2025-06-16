package com.craftzdev.tasktracker.mapper;

import com.craftzdev.tasktracker.dto.TaskDto;
import com.craftzdev.tasktracker.entity.Task;
import com.craftzdev.tasktracker.entity.TaskPriority;
import com.craftzdev.tasktracker.entity.TaskStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    @Mapping(target = "status", expression = "java(getStatus(taskDto))")
    @Mapping(target = "priority", expression = "java(getPriority(taskDto))")
    Task toEntity(TaskDto taskDto);

    TaskDto toDto(Task task);

    default TaskStatus getStatus(TaskDto taskDto) {
        return taskDto.status() != null ? taskDto.status() : TaskStatus.OPEN;
    }

    default TaskPriority getPriority(TaskDto taskDto) {
        return taskDto.priority() != null ? taskDto.priority() : TaskPriority.LOW;
    }

    @Mapping(target = "status", expression = "java(taskDto.status() != null ? taskDto.status() : taskEntity.getStatus())")
    @Mapping(target = "priority", expression = "java(taskDto.priority() != null ? taskDto.priority" + "() " + ": taskEntity.getPriority())")
    @Mapping(target = "id", ignore = true)
    void updateTaskFromDto(TaskDto taskDto, @MappingTarget Task taskEntity);
}
