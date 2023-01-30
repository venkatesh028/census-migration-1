package com.ideas2it.censusmigration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ideas2it.censusmigration.model.TargetMapping;
public interface TargetMappingRepository extends JpaRepository<TargetMapping, Long> {
}
