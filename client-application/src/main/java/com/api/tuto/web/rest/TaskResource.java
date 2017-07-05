package com.api.tuto.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by youssefguenoun on 28/06/2017.
 */
@RestController
@RequestMapping("/tasks")
public class TaskResource {

    @Value("#{'${service.url}'.trim()}")
    private String serviceUrl;


    @GetMapping
    public ResponseEntity taskDtos() throws URISyntaxException {


        return ResponseEntity.ok(null);
    }
}
