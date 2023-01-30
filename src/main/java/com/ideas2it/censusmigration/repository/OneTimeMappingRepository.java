package com.ideas2it.censusmigration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.censusmigration.model.EhrMapping;

public interface OneTimeMappingRepository extends JpaRepository<EhrMapping, Long> {

    List<EhrMapping> findAllBySourceEhrName(String sourceEhrName);

    List<EhrMapping> findAllBySourceEhrNameAndServiceLineAndTargetEhrName(String sourceEhrName,
                                                                          String serviceLine,
                                                                          String targetEhrName);
}
