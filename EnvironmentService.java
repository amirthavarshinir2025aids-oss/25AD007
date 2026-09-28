package com.example.FeatureFlagLite.service;

import com.example.FeatureFlagLite.entity.Environment;
import com.example.FeatureFlagLite.repository.EnvironmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentService {

    private final EnvironmentRepository environmentRepository;

    public EnvironmentService(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }

    public Environment createEnvironment(Environment environment) {
        return environmentRepository.save(environment);
    }

    public List<Environment> getAllEnvironments() {
        return environmentRepository.findAll();
    }
}