package com.nextecommerce.commerce.annotation;

import com.nextecommerce.commerce.enums.ActivityLogScope;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityLog {

    ActivityLogScope scope();

    String messageKey();

    Class<? extends Annotation> payloadSource() default RequestBody.class;

}
