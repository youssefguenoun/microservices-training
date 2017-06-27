package com.api.tuto;


import com.api.tuto.domain.Task;
import com.api.tuto.repository.TaskRepository;
import com.api.tuto.util.PaginationUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/tasks")
public class TaskResource {

    @Autowired
    private TaskRepository taskRepository;


    @Transactional(readOnly = true)
    @GetMapping
    ResponseEntity<List<Task>> getTasks(Pageable pageable) throws URISyntaxException {
        Page<Task> page = taskRepository.findAll(pageable);

        //Should be configured with the full url of the service
        String baseUrl = "/api/tasks";
        HttpHeaders paginationHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tasks");
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    ResponseEntity<Task> getTask(@PathVariable Long id) {
        Optional<Task> task = Optional.ofNullable(taskRepository.findOne(id));
        if(task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws URISyntaxException {
        Task result = taskRepository.save(task);
        URI newRessourceURI = new URI("/api/tasks/" + result.getId());
        return ResponseEntity.created(newRessourceURI).body(task);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) throws URISyntaxException {
        task.setId(id);
        Task result = taskRepository.save(task);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws URISyntaxException {
        taskRepository.delete(id);
        return ResponseEntity.noContent().build();
    }



}
