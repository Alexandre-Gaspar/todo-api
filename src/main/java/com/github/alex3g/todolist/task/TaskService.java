package com.github.alex3g.todolist.task;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskModel createTask(TaskDTO dto) {
        var task = new TaskModel(dto);

        return this.repository.save(task);
    }
}
