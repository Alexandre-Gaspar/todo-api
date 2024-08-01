package com.github.alex3g.todolist.task;

import com.github.alex3g.todolist.task.enums.PriorityEnum;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50, nullable = false)
    private String title;

    private String description;

    @Column(name = "stars_at", nullable = false)
    private LocalDateTime startsAt;

    @Column(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;

    @Column(name = "created_at")
    @Timestamp
    private LocalDateTime createdAt;

    private UUID idUser;

    public TaskModel(TaskDTO data) {
        this.title = data.title();
        this.description = data.description();
        this.startsAt = LocalDateTime.parse(data.startsAt());
        this.endsAt = LocalDateTime.parse(data.endsAt());
        this.priority = data.priority();
        this.createdAt = LocalDateTime.now();
    }
}
