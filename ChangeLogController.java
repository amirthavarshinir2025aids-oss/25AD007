package com.example.FeatureFlagLite.controller;

import com.example.FeatureFlagLite.entity.ChangeLog;
import com.example.FeatureFlagLite.service.ChangeLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/change-logs")
public class ChangeLogController {

    private final ChangeLogService changeLogService;

    public ChangeLogController(ChangeLogService changeLogService) {
        this.changeLogService = changeLogService;
    }

    // Get all change history
    @GetMapping
    public ResponseEntity<List<ChangeLog>> getAllChangeLogs() {

        return ResponseEntity.ok(
                changeLogService.getAllChangeLogs()
        );
    }
}