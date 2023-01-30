package com.ideas2it.censusmigration.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ideas2it.censusmigration.model.EhrMapping;
import com.ideas2it.censusmigration.repository.OneTimeMappingRepository;
import com.ideas2it.censusmigration.service.OneTimeMappingService;

@Service
public class OneTimeMappingServiceImpl implements OneTimeMappingService {
    private final OneTimeMappingRepository oneTimeMappingRepository;

    public OneTimeMappingServiceImpl(OneTimeMappingRepository oneTimeMappingRepository) {
        this.oneTimeMappingRepository = oneTimeMappingRepository;
    }

    @Override
    public EhrMapping createEhrMapping(EhrMapping ehrMapping){
        ehrMapping.setMappingId(UUID.randomUUID());
        return oneTimeMappingRepository.save(ehrMapping);
    }

    @Override
    public EhrMapping updateEhrMapping(EhrMapping ehrMapping) {
        return oneTimeMappingRepository.save(ehrMapping);
    }

    @Override
    public List<EhrMapping> getEhrMappingsBasedOnSourceEhrAndServiceLine(String sourceEhr, String serviceLine) {
        return oneTimeMappingRepository.findAllBySourceEhrNameAndServiceLineAndTargetEhrName(sourceEhr,
                serviceLine, "HCHB");
    }

    @Override
    public List<EhrMapping> getEhrMappingsBasedOnSourceEhrName(String sourceEhrName) {
        return oneTimeMappingRepository.findAllBySourceEhrName(sourceEhrName);
    }
}
