package com.example.FeatureFlagLite.controller;

import com.example.FeatureFlagLite.entity.FeatureFlag;
import com.example.FeatureFlagLite.service.FeatureFlagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feature-flags")
public class FeatureFlagController {

    private final FeatureFlagService featureFlagService;

    public FeatureFlagController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    @PostMapping
    public ResponseEntity<FeatureFlag> createFeatureFlag(
            @RequestBody FeatureFlag featureFlag) {

        FeatureFlag createdFlag =
                featureFlagService.createFeatureFlag(featureFlag);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdFlag);
    }

    @GetMapping
    public ResponseEntity<List<FeatureFlag>> getAllFeatureFlags() {

        return ResponseEntity.ok(
                featureFlagService.getAllFeatureFlags()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureFlag> getFeatureFlagById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                featureFlagService.getFeatureFlagById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeatureFlag> updateFeatureFlag(
            @PathVariable Long id,
            @RequestBody FeatureFlag featureFlag) {

        return ResponseEntity.ok(
                featureFlagService.updateFeatureFlag(id, featureFlag)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeatureFlag(
            @PathVariable Long id) {

        featureFlagService.deleteFeatureFlag(id);

        return ResponseEntity.noContent().build();
    }
}