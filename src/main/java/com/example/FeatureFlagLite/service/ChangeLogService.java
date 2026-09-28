package com.example.FeatureFlagLite.service;

import com.example.FeatureFlagLite.entity.ChangeLog;
import com.example.FeatureFlagLite.repository.ChangeLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeLogService {

    private final ChangeLogRepository changeLogRepository;

    public ChangeLogService(ChangeLogRepository changeLogRepository) {
        this.changeLogRepository = changeLogRepository;
    }

    public ChangeLog createChangeLog(ChangeLog changeLog) {
        return changeLogRepository.save(changeLog);
    }

    public List<ChangeLog> getAllChangeLogs() {
        return changeLogRepository.findAll();
    }
}