package com.ideas2it.censusmigration.repository;

import com.ideas2it.censusmigration.model.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
}
