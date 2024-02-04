package com.nextecommerce.commerce.services;


import com.nextecommerce.commerce.enums.ActivityLogStatus;

public interface ActivityLogService {

    void log(ActivityLogStatus status, String title, String description);

    void log(ActivityLogStatus status, String title, String description, String payload);

    void log(ActivityLogStatus status, String title, String description, String payload, String result);

}
