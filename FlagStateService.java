package com.example.FeatureFlagLite.service;

import com.example.FeatureFlagLite.entity.FlagState;
import com.example.FeatureFlagLite.repository.FlagStateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlagStateService {

    private final FlagStateRepository flagStateRepository;

    public FlagStateService(FlagStateRepository flagStateRepository) {
        this.flagStateRepository = flagStateRepository;
    }

    public FlagState createFlagState(FlagState flagState) {
        return flagStateRepository.save(flagState);
    }

    public List<FlagState> getAllFlagStates() {
        return flagStateRepository.findAll();
    }
}