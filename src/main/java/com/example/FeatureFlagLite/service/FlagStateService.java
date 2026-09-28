package com.example.FeatureFlagLite.service;

import com.example.FeatureFlagLite.entity.ChangeLog;
import com.example.FeatureFlagLite.entity.Environment;
import com.example.FeatureFlagLite.entity.FeatureFlag;
import com.example.FeatureFlagLite.entity.FlagState;
import com.example.FeatureFlagLite.entity.State;
import com.example.FeatureFlagLite.repository.ChangeLogRepository;
import com.example.FeatureFlagLite.repository.EnvironmentRepository;
import com.example.FeatureFlagLite.repository.FeatureFlagRepository;
import com.example.FeatureFlagLite.repository.FlagStateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlagStateService {

    private final FlagStateRepository flagStateRepository;
    private final FeatureFlagRepository featureFlagRepository;
    private final EnvironmentRepository environmentRepository;
    private final ChangeLogRepository changeLogRepository;

    public FlagStateService(
            FlagStateRepository flagStateRepository,
            FeatureFlagRepository featureFlagRepository,
            EnvironmentRepository environmentRepository,
            ChangeLogRepository changeLogRepository) {

        this.flagStateRepository = flagStateRepository;
        this.featureFlagRepository = featureFlagRepository;
        this.environmentRepository = environmentRepository;
        this.changeLogRepository = changeLogRepository;
    }

    // Create or update a flag state for a specific environment
    public FlagState createOrUpdateFlagState(
            Long featureFlagId,
            Long environmentId,
            State state,
            Integer rolloutPercentage) {

        // Validate rollout percentage
        if (rolloutPercentage == null ||
                rolloutPercentage < 0 ||
                rolloutPercentage > 100) {

            throw new IllegalArgumentException(
                    "Rollout percentage must be between 0 and 100"
            );
        }

        // Find feature flag
        FeatureFlag featureFlag =
                featureFlagRepository.findById(featureFlagId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Feature flag not found"));

        // Find environment
        Environment environment =
                environmentRepository.findById(environmentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Environment not found"));

        // Find existing state or create a new one
        FlagState flagState =
                flagStateRepository
                        .findByFeatureFlagIdAndEnvironmentId(
                                featureFlagId,
                                environmentId
                        )
                        .orElse(new FlagState());

        // Store old values before updating
        State oldState = flagState.getState();

        // If this is the first environment-specific state,
        // use the feature flag's default state
        if (oldState == null) {
            oldState = featureFlag.getDefaultState();
        }

        Integer oldRolloutPercentage =
                flagState.getRolloutPercentage();

        // Update flag state
        flagState.setFeatureFlag(featureFlag);
        flagState.setEnvironment(environment);
        flagState.setState(state);
        flagState.setRolloutPercentage(rolloutPercentage);

        // Save flag state
        FlagState savedFlagState =
                flagStateRepository.save(flagState);

        // Create change history
        ChangeLog changeLog = new ChangeLog();

        changeLog.setFeatureFlag(featureFlag);
        changeLog.setEnvironment(environment);
        changeLog.setOldState(oldState);
        changeLog.setNewState(state);
        changeLog.setOldRolloutPercentage(
                oldRolloutPercentage
        );
        changeLog.setNewRolloutPercentage(
                rolloutPercentage
        );
        changeLog.setChangedBy("admin");
        changeLog.setChangedAt(LocalDateTime.now());

        // Save change history
        changeLogRepository.save(changeLog);

        return savedFlagState;
    }

    // Get one flag state for one environment
    public FlagState getFlagState(
            Long featureFlagId,
            Long environmentId) {

        return flagStateRepository
                .findByFeatureFlagIdAndEnvironmentId(
                        featureFlagId,
                        environmentId
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Flag state not found"));
    }

    // Get all flag states
    public List<FlagState> getAllFlagStates() {

        return flagStateRepository.findAll();
    }

    // Get flag states for a specific application and environment
    public List<FlagState> getFlagsForApplicationAndEnvironment(
            String application,
            String environmentName) {

        return flagStateRepository
                .findByFeatureFlagApplicationAndEnvironmentName(
                        application,
                        environmentName
                );
    }
}