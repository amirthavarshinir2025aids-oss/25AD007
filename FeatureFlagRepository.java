package com.example.FeatureFlagLite.repository;

import com.example.FeatureFlagLite.entity.FeatureFlag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureFlagRepository extends JpaRepository<FeatureFlag, Long> {
}