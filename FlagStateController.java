package com.example.FeatureFlagLite.controller;

import com.example.FeatureFlagLite.entity.FlagState;
import com.example.FeatureFlagLite.entity.State;
import com.example.FeatureFlagLite.service.FlagStateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flag-states")
public class FlagStateController {

    private final FlagStateService flagStateService;

    public FlagStateController(FlagStateService flagStateService) {
        this.flagStateService = flagStateService;
    }

    // Create or update flag state
    @PutMapping
    public ResponseEntity<FlagState> createOrUpdateFlagState(
            @RequestParam Long featureFlagId,
            @RequestParam Long environmentId,
            @RequestParam State state,
            @RequestParam Integer rolloutPercentage) {

        FlagState flagState =
                flagStateService.createOrUpdateFlagState(
                        featureFlagId,
                        environmentId,
                        state,
                        rolloutPercentage
                );

        return ResponseEntity.ok(flagState);
    }

    // Get all flag states
    @GetMapping
    public ResponseEntity<List<FlagState>> getAllFlagStates() {

        return ResponseEntity.ok(
                flagStateService.getAllFlagStates()
        );
    }

    // Get one flag state for one environment
    @GetMapping("/single")
    public ResponseEntity<FlagState> getFlagState(
            @RequestParam Long featureFlagId,
            @RequestParam Long environmentId) {

        return ResponseEntity.ok(
                flagStateService.getFlagState(
                        featureFlagId,
                        environmentId
                )
        );
    }

    // Runtime API
    @GetMapping("/runtime")
    public ResponseEntity<List<FlagState>>
    getFlagsForApplicationAndEnvironment(
            @RequestParam String application,
            @RequestParam String environment) {

        return ResponseEntity.ok(
                flagStateService
                        .getFlagsForApplicationAndEnvironment(
                                application,
                                environment
                        )
        );
    }
}