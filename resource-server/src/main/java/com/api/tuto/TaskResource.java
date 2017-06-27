package com.api.tuto;


import com.api.tuto.domain.Task;
import com.api.tuto.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskResource {

    @Autowired
    private TaskRepository taskRepository;

    List<Task> getTasks()  {
        List<Task> tasks = taskRepository.findAll();

        return tasks;
    }



}