package com.ideas2it.censusmigration.service;

import java.util.List;

import com.ideas2it.censusmigration.model.EhrMapping;

public interface OneTimeMappingService {

    EhrMapping createEhrMapping(EhrMapping ehrMapping);

    EhrMapping updateEhrMapping(EhrMapping ehrMapping);

    List<EhrMapping> getEhrMappingsBasedOnSourceEhrAndServiceLine(String sourceEhr, String serviceLine);

    List<EhrMapping> getEhrMappingsBasedOnSourceEhrName(String sourceEhrName);
}
