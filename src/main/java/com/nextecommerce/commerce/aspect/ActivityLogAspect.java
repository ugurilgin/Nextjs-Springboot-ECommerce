package com.nextecommerce.commerce.aspect;


import com.nextecommerce.commerce.annotation.ActivityLog;
import com.nextecommerce.commerce.enums.ActivityLogStatus;
import com.nextecommerce.commerce.services.ActivityLogService;
import com.nextecommerce.commerce.utils.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ActivityLogAspect {

    private final ActivityLogService activityLogService;

    @AfterReturning(value = "@annotation(com.nextecommerce.commerce.annotation.ActivityLog)", returning = "returnValue")
    public void logActivitySuccess(JoinPoint joinPoint, Object returnValue) {
        logActivity(joinPoint, returnValue, null);

    }

    @AfterThrowing(value = "@annotation(com.nextecommerce.commerce.annotation.ActivityLog)", throwing = "ex")
    public void logActivityError(JoinPoint joinPoint, Throwable ex) {
        logActivity(joinPoint, null, ex);
    }


    private void logActivity(JoinPoint joinPoint, Object returnValue, Throwable ex) {
        var signature = (MethodSignature) joinPoint.getSignature();
        var method = signature.getMethod();
        var activityAnnotation = method.getAnnotation(ActivityLog.class);
        var payloadSource = activityAnnotation.payloadSource();
        var subjectKey = activityAnnotation.subjectKey();
        var payloadObject = getPayload(payloadSource, joinPoint.getArgs(), method.getParameterAnnotations());
        var payloadString = ObjectMapperUtil.toJsonString(payloadObject);
        var returnValueString = ObjectMapperUtil.toJsonString(returnValue);

        var scope = activityAnnotation.scope();
        var messageKey = activityAnnotation.messageKey();
        var descriptionKey = Objects.isNull(ex)
                ? String.format("%s.description", messageKey)
                : String.format("%s.error", messageKey);

        var title = messageKey;
        var description = descriptionKey;
        var subject = subjectKey;
        var status = Objects.isNull(ex)
                ? ActivityLogStatus.SUCCESS
                : ActivityLogStatus.ERROR;

        switch (scope) {
            case BASIC -> activityLogService.log(status, title, description,subject);
            case DETAILED -> activityLogService.log(status, title, description, payloadString,subject);
            case FULL -> activityLogService.log(status, title, description, payloadString, returnValueString,subject);
        }
    }

    private Object getPayload(Class<? extends Annotation> payloadSource, Object[] args, Annotation[][] annotations) {
        if (ArrayUtils.isEmpty(args)) {
            return null;
        }
        var requestBodyArgIndex = new AtomicReference<Integer>();
        IntStream.range(0, annotations.length).forEach(i -> {
            var parameterAnnotations = annotations[i];
            IntStream.range(0, parameterAnnotations.length).forEach(j -> {
                var parameterAnnotation = parameterAnnotations[j];
                if (Objects.equals(parameterAnnotation.annotationType(), payloadSource)) {
                    requestBodyArgIndex.set(i);
                }
            });
        });
        if (Objects.isNull(requestBodyArgIndex.get())) {
            return null;
        }
        return args[requestBodyArgIndex.get()];
    }

}
