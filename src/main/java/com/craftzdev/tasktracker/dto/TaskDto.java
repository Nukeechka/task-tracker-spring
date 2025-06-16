package com.craftzdev.tasktracker.dto;

import com.craftzdev.tasktracker.entity.TaskPriority;
import com.craftzdev.tasktracker.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;

public record TaskDto(
        Long id,

        @NotBlank(message = "Title should not be empty")
        String title,

        @NotBlank(message = "Description should not be empty")
        String description,

        TaskPriority priority,

        TaskStatus status
) {
}
