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
@RequestMapping("/api")
public class TaskResource {

    @Autowired
    private TaskRepository taskRepository;

    private int version = 0;

    @ApiOperation(value = "Gets 'Task' objects.", notes = "Optional query param of **size** determines size of returned array", response = Task.class, responseContainer = "List")
    @ApiResponses(value = {@io.swagger.annotations.ApiResponse(code = 200, message = "Successful response") })
    @Transactional(readOnly = true)
    @RequestMapping(path="/tasks", method = RequestMethod.GET)
    @CrossOrigin(origins ="http://editor.swagger.io")
    ResponseEntity<List<Task>> getTasks(Pageable pageable) throws URISyntaxException {
        Page<Task> page = taskRepository.findAll(pageable);

        //Should be configured with the full url of the service
        String baseUrl = "/api/tasks";
        HttpHeaders paginationHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tasks");
        CacheControl cacheControl = CacheControl.noCache().cachePublic().maxAge(2, TimeUnit.DAYS);
        String etag = "V" + version;
        return ResponseEntity.ok().cacheControl(cacheControl).eTag(etag).body(page.getContent());
    }

    @RequestMapping(path ="/tasks/{id}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    ResponseEntity<Task> getTask(@PathVariable Long id) {
        Optional<Task> task = Optional.ofNullable(taskRepository.findOne(id));
        if(task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws URISyntaxException {
        Task result = taskRepository.save(task);
        URI newRessourceURI = new URI("/api/tasks/" + result.getId());
        version++;
        return ResponseEntity.created(newRessourceURI).body(task);
    }


    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) throws URISyntaxException {
        task.setId(id);
        Task result = taskRepository.save(task);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws URISyntaxException {
        taskRepository.delete(id);
        return ResponseEntity.noContent().build();
    }



}