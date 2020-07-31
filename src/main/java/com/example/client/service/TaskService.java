package com.example.client.service;

import com.example.client.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();
    Task update(Long id, Task task);
    void delete(Long id);
    Task create(Task task);
    Task findById(Long id);

}
