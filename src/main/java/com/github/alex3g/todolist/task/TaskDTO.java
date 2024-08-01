package com.github.alex3g.todolist.task;

import com.github.alex3g.todolist.task.enums.PriorityEnum;

public record TaskDTO(
        String title,
        String description,
        String startsAt,
        String endsAt,
        PriorityEnum priority
) {
}
