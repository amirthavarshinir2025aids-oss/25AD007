package com.example.FeatureFlagLite.repository;

import com.example.FeatureFlagLite.entity.FlagState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlagStateRepository extends JpaRepository<FlagState, Long> {
}