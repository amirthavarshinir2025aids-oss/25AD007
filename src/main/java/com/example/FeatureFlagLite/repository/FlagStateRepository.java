package com.example.FeatureFlagLite.repository;

import com.example.FeatureFlagLite.entity.FlagState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlagStateRepository extends JpaRepository<FlagState, Long> {

    Optional<FlagState> findByFeatureFlagIdAndEnvironmentId(
            Long featureFlagId,
            Long environmentId
    );

    List<FlagState> findByFeatureFlagApplicationAndEnvironmentName(
            String application,
            String environmentName
    );
}