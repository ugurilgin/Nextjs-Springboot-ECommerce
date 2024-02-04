package com.nextecommerce.commerce.services.impl;

import com.nextecommerce.commerce.entities.ActivityLog;
import com.nextecommerce.commerce.enums.ActivityLogStatus;
import com.nextecommerce.commerce.repositories.ActivityLogRepository;
import com.nextecommerce.commerce.services.ActivityLogService;
import com.nextecommerce.commerce.utils.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    private final HttpServletRequest request;

    private final IpUtil ipUtil;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(ActivityLogStatus status, String title, String description,String subject) {

        var activity = ActivityLog.builder()
                .status(status)
                .source(request.getRequestURI())
                .ipAddress(ipUtil.getClientIpAddr(request))
                .subject(subject)
                .title(title)
                .description(description)
                .build();
        activityLogRepository.save(activity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(ActivityLogStatus status, String title, String description, String payload,String subject) {

        var activity = ActivityLog.builder()
                .status(status)
                .source(request.getRequestURI())
                .ipAddress(request.getRemoteAddr())
                .title(title)
                .subject(subject)
                .description(description)
                .payload(payload)
                .build();
        activityLogRepository.save(activity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(ActivityLogStatus status, String title, String description, String payload, String result,String subject) {

        var activity = ActivityLog.builder()
                .status(status)
                .source(request.getRequestURI())
                .ipAddress(request.getRemoteAddr())
                .title(title)
                .subject(subject)
                .description(description)
                .payload(payload)
                .result(result)
                .build();
        activityLogRepository.save(activity);
    }
}
