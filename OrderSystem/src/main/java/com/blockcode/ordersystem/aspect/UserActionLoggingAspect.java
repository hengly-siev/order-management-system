package com.blockcode.ordersystem.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class UserActionLoggingAspect {

    // Use the dedicated user action logger
    private static final Logger userLogger = LoggerFactory.getLogger("USER_ACTION_LOGGER");

    // Pointcut to intercept all methods within classes annotated with @RestController
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerMethods() {}

    @Around("restControllerMethods()")
    public Object logUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        // Retrieve current HTTP request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        // Get the current authenticated username, if available
        String username = "anonymous";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            username = auth.getName();
        }

        String httpMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        String invokedMethod = joinPoint.getSignature().toShortString();

        // Log the start of the user action
        userLogger.info("ACTION START: User '{}' invoked [{} {}] --> {}", username, httpMethod, requestURI, invokedMethod);

        try {
            Object result = joinPoint.proceed();
            // Log successful completion
            userLogger.info("ACTION SUCCESS: User '{}' completed [{} {}] --> {}", username, httpMethod, requestURI, invokedMethod);
            return result;
        } catch (Throwable ex) {
            // Log failure along with exception message
            userLogger.error("ACTION FAILURE: User '{}' encountered error on [{} {}] --> {}. Exception: {}",
                    username, httpMethod, requestURI, invokedMethod, ex.getMessage(), ex);
            throw ex;
        }
    }
}