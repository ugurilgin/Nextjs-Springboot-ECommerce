package com.nextecommerce.commerce.annotation;

import com.nextecommerce.commerce.enums.EventType;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.UUID;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventPublish {

     String topic();

     EventType eventType();

    Class<? extends Annotation> payloadSource() default RequestBody.class;


}
