package com.example.client.controller;

import com.example.client.model.Task;
import com.example.client.model.User;
import com.example.client.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class TaskController {
    @Autowired
    private TaskService service;
    @Autowired
    private ObjectMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("tasks", service.findAll());
        model.addAttribute("newTask", new Task());
        return "tasks";
    }

    @RequestMapping(value ="put", method = RequestMethod.POST)
    public String update(Task task) {
        Task temp = service.findById(task.getId());
        task.setUser(temp.getUser());
        service.update(task.getId(), task);
        return "redirect:/";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("newTask") Task task) {
        service.create(task);
        return "redirect:/";
    }
}
