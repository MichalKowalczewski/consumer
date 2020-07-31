package com.example.client.service;

import com.example.client.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Value("${resource.tasks}")
    private String resource;
    @Value("${resource.tasks}/{id}")
    private String idResource;
    @Autowired
    private RestTemplate restTemplate;

    public List<Task> findAll() {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(resource, Task[].class))).collect(Collectors.toList());
    }

    public Task update(Long id, Task task) {
        return restTemplate.exchange(idResource, HttpMethod.PUT, new HttpEntity<>(task), Task.class, id).getBody();
    }

    public void delete(Long id) {
        restTemplate.delete(idResource, id);
    }

    public Task create(Task task) {
        return restTemplate.postForObject(resource, task, Task.class);
    }

    @Override
    public Task findById(Long id) {
        return restTemplate.getForObject(idResource, Task.class, id);
    }
}
