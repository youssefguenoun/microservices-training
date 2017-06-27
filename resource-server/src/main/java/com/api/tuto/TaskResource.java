package com.api.tuto;


import com.api.tuto.domain.Task;
import com.api.tuto.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskResource {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    @RequestMapping(path="/tasks", method = RequestMethod.GET)
    @CrossOrigin(origins ="http://editor.swagger.io")
    List<Task> getTasks()  {
        List<Task> tasks = taskRepository.findAll();

        return tasks;
    }



}