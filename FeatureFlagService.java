package com.example.FeatureFlagLite.service;

import com.example.FeatureFlagLite.entity.FeatureFlag;
import com.example.FeatureFlagLite.repository.FeatureFlagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureFlagService {

    private final FeatureFlagRepository featureFlagRepository;

    public FeatureFlagService(FeatureFlagRepository featureFlagRepository) {
        this.featureFlagRepository = featureFlagRepository;
    }

    public FeatureFlag createFeatureFlag(FeatureFlag featureFlag) {
        return featureFlagRepository.save(featureFlag);
    }

    public List<FeatureFlag> getAllFeatureFlags() {
        return featureFlagRepository.findAll();
    }
}