package com.example.FeatureFlagLite.controller;

import com.example.FeatureFlagLite.entity.Environment;
import com.example.FeatureFlagLite.service.EnvironmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/environments")
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @PostMapping
    public ResponseEntity<Environment> createEnvironment(
            @RequestBody Environment environment) {

        Environment createdEnvironment =
                environmentService.createEnvironment(environment);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdEnvironment);
    }

    @GetMapping
    public ResponseEntity<List<Environment>> getAllEnvironments() {

        return ResponseEntity.ok(
                environmentService.getAllEnvironments()
        );
    }
}