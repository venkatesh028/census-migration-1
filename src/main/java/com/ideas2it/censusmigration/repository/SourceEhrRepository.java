package com.ideas2it.censusmigration.repository;

import com.ideas2it.EhrFileOperations.model.SourceExcelRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceEhrRepository extends JpaRepository<SourceExcelRecord, Integer> {
}
