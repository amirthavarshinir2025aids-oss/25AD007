package com.example.FeatureFlagLite.repository;

import com.example.FeatureFlagLite.entity.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentRepository extends JpaRepository<Environment, Long> {
}