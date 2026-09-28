package com.example.FeatureFlagLite.service;

import com.example.FeatureFlagLite.entity.FeatureFlag;
import com.example.FeatureFlagLite.repository.FeatureFlagRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeatureFlagService {

    private final FeatureFlagRepository featureFlagRepository;

    public FeatureFlagService(FeatureFlagRepository featureFlagRepository) {
        this.featureFlagRepository = featureFlagRepository;
    }

    // Create a new feature flag
    public FeatureFlag createFeatureFlag(FeatureFlag featureFlag) {

        LocalDateTime now = LocalDateTime.now();

        featureFlag.setCreatedAt(now);
        featureFlag.setUpdatedAt(now);

        return featureFlagRepository.save(featureFlag);
    }

    // Get all feature flags
    public List<FeatureFlag> getAllFeatureFlags() {
        return featureFlagRepository.findAll();
    }

    // Get feature flag by ID
    public FeatureFlag getFeatureFlagById(Long id) {
        return featureFlagRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Feature flag not found"));
    }

    // Update an existing feature flag
    public FeatureFlag updateFeatureFlag(
            Long id,
            FeatureFlag updatedFlag) {

        FeatureFlag existingFlag =
                getFeatureFlagById(id);

        existingFlag.setName(updatedFlag.getName());
        existingFlag.setDescription(updatedFlag.getDescription());
        existingFlag.setDefaultState(updatedFlag.getDefaultState());
        existingFlag.setApplication(updatedFlag.getApplication());

        // Automatically update modification time
        existingFlag.setUpdatedAt(LocalDateTime.now());

        return featureFlagRepository.save(existingFlag);
    }

    // Delete feature flag
    public void deleteFeatureFlag(Long id) {

        FeatureFlag existingFlag =
                getFeatureFlagById(id);

        featureFlagRepository.delete(existingFlag);
    }
}