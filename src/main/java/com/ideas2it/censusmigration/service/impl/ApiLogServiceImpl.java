package com.ideas2it.censusmigration.service.impl;

import com.ideas2it.EhrFileOperations.model.ApiLog;
import com.ideas2it.EhrFileOperations.repository.ApiLogRepository;
import com.ideas2it.EhrFileOperations.service.ApiLogService;
import org.springframework.stereotype.Service;

@Service
public class ApiLogServiceImpl implements ApiLogService {
    private final ApiLogRepository apiLogRepository;

    public ApiLogServiceImpl(ApiLogRepository apiLogRepository) {
        this.apiLogRepository = apiLogRepository;
    }

    @Override
    public void saveApiLog(ApiLog apiLog) {
        apiLogRepository.save(apiLog);
    }
}
