package com.nextecommerce.commerce.aspect;


import com.nextecommerce.commerce.annotation.ActivityLog;
import com.nextecommerce.commerce.annotation.EventPublish;
import com.nextecommerce.commerce.dtos.requests.OrderRequestDTO;
import com.nextecommerce.commerce.enums.ActivityLogStatus;
import com.nextecommerce.commerce.enums.EventType;
import com.nextecommerce.commerce.event.events.OrderEvent;
import com.nextecommerce.commerce.event.publisher.EventPublisher;
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
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class EventPublishAspect {
    private final EventPublisher publisher;

    @AfterReturning(value = "@annotation(com.nextecommerce.commerce.annotation.EventPublish)", returning = "returnValue")
    public void logActivitySuccess(JoinPoint joinPoint, Object returnValue) {
        logActivity(joinPoint, returnValue, null);

    }

    @AfterThrowing(value = "@annotation(com.nextecommerce.commerce.annotation.EventPublish)", throwing = "ex")
    public void logActivityError(JoinPoint joinPoint, Throwable ex) {
        logActivity(joinPoint, null, ex);
    }


    private void logActivity(JoinPoint joinPoint, Object returnValue, Throwable ex) {
        var signature = (MethodSignature) joinPoint.getSignature();
        var method = signature.getMethod();
        var activityAnnotation = method.getAnnotation(EventPublish.class);
        var payloadSource = activityAnnotation.payloadSource();
        var payloadObject = getPayload(payloadSource, joinPoint.getArgs(), method.getParameterAnnotations());

        var topic = activityAnnotation.topic();
        var eventType = activityAnnotation.eventType();

        UUID uuid = UUID.randomUUID();

        OrderEvent event= OrderEvent.builder().
                key(uuid).
                eventType(eventType).
                topic(topic).
                request((OrderRequestDTO) payloadObject).
                build();
        publisher.publishApplicationEvent(topic,eventType,event);
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
