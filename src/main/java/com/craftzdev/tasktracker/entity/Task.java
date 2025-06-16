package com.craftzdev.tasktracker.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tasks")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    TaskStatus status;

    @CreationTimestamp
    @Column(name = "created", nullable = false)
    LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated", nullable = false)
    LocalDateTime updated;
}
