package com.example.FeatureFlagLite.repository;

import com.example.FeatureFlagLite.entity.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeLogRepository extends JpaRepository<ChangeLog, Long> {
}