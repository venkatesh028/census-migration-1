package com.ideas2it.censusmigration.util.advice;

import com.ideas2it.censusmigration.model.ApiLog;
import com.ideas2it.censusmigration.service.ApiLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ApiLogAdvice {
    private final ApiLogService apiLogService;

    public ApiLogAdvice(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }
    @Around("execution(* com.ideas2it.censusmigration.controller.*.*(..))")
    public Object logApiCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest servletRequest = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        Object[] args = joinPoint.getArgs();
        Map<String, Object> fileNames = new HashMap<>();

        for (Object arg : args) {
            if (arg instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) arg;
                fileNames.put(file.getName(),file.getOriginalFilename());
                String fileName = file.getOriginalFilename();
                System.out.println("File name: " + fileName);
                // Do something with the file name
            }
        }
        ApiLog apiLog = new ApiLog();
        apiLog.setUser("current user"); //servletRequest.getUserPrincipal().getName();
        apiLog.setUrl(servletRequest.getRequestURL().toString());
        apiLog.setMethod(servletRequest.getMethod());
        apiLog.setRequestHeaders(servletRequest.getHeader("Authorization"));
        apiLog.setRequestAttachments(fileNames);
        apiLog.setRequestTime(LocalDateTime.now());
        Object result = joinPoint.proceed();
        if (result instanceof ResponseEntity<?>) {
            apiLog.setStatus(((ResponseEntity<?>) result).getStatusCode().value());
            apiLog.setResponseHeaders(((ResponseEntity<?>) result).getHeaders().toString());
            apiLog.setResponseBody(((ResponseEntity<?>) result).getBody().toString());
        }
        apiLogService.saveApiLog(apiLog);
        return result;
    }
}

//        System.out.println(file.getOriginalFilename());
//                System.out.println(file.getName());
//                Object[] args = joinPoint.getArgs();
//                for (Object arg: args) {
//                System.out.println(arg.toString());
//
//                if (arg instanceof StandardMultipartHttpServletRequest) {
//                System.out.println("I am here");
//                StandardMultipartHttpServletRequest request = (StandardMultipartHttpServletRequest) arg;
//                if (servletRequest.getContentType().contains("multipart/form-data")) {
//                Map<String, MultipartFile> fileMap = request.getFileMap();
//        for (MultipartFile file : fileMap.values()
//        ) {
//        System.out.println("Filename: " + file.getOriginalFilename());
//        }
//        }
//                List<String> parameterNames= Collections.list(servletRequest.getParameterNames());
//        for (String attributeName: parameterNames
//             ) {
//            System.out.println(attributeName);
//
//        }
//        }
//        }        if (null != ((ServletServerHttpRequest)servletRequest).getBody()) {
//        apiLog.setRequestBody(((ServletServerHttpRequest)servletRequest).getBody().toString());
//        }