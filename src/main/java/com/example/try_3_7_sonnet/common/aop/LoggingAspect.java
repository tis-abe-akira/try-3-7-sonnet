package com.example.try_3_7_sonnet.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointcut() {
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void servicePointcut() {
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryPointcut() {
    }

    @Pointcut("within(org.springdoc..*) || within(springfox..*)")
    public void apiDocPointcut() {
    }

    @Pointcut("execution(public * org.springframework.data.repository.Repository+.*(..))")
    public void springDataRepositoryPointcut() {
    }

    @Around("(controllerPointcut() || servicePointcut() || repositoryPointcut()) && !apiDocPointcut() && !springDataRepositoryPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with args = {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    formatArgs(joinPoint.getArgs()));
        }

        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}",
                        joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(),
                        formatReturnValue(result));
            }
            return result;
        } catch (Exception e) {
            log.error("Exception in {}.{}() with cause = {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    e.getCause() != null ? e.getCause() : "NULL");
            throw e;
        }
    }

    /**
     * 引数を整形して返します。大きなオブジェクトや配列の場合は省略表示します。
     */
    private String formatArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return "{}";
        }
        return Arrays.stream(args)
                .map(this::formatObject)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    /**
     * 戻り値を整形して返します。大きなオブジェクトや配列の場合は省略表示します。
     */
    private String formatReturnValue(Object result) {
        return formatObject(result);
    }

    /**
     * オブジェクトを整形して返します。大きなオブジェクトや配列の場合は省略表示します。
     */
    private String formatObject(Object obj) {
        if (obj == null) {
            return "null";
        }

        // バイト配列の場合は特別に処理
        if (obj instanceof byte[]) {
            byte[] bytes = (byte[]) obj;
            if (bytes.length > 100) {
                return String.format("<byte[]:%d bytes>", bytes.length);
            }
        }

        // 文字列の場合は長さチェック
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() > 200) {
                return String.format("\"%s...\" (length: %d)", str.substring(0, 197), str.length());
            }
        }

        // コレクションや配列の場合は要素数のみ表示
        if (obj instanceof java.util.Collection) {
            java.util.Collection<?> collection = (java.util.Collection<?>) obj;
            return String.format("<%s with %d elements>", obj.getClass().getSimpleName(), collection.size());
        }

        if (obj.getClass().isArray()) {
            int length = java.lang.reflect.Array.getLength(obj);
            return String.format("<%s with %d elements>", obj.getClass().getSimpleName(), length);
        }

        // 一般的なオブジェクトの場合
        String objString = obj.toString();
        if (objString.length() > 200) {
            return String.format("%s... (length: %d)", objString.substring(0, 197), objString.length());
        }

        return objString;
    }

    @AfterThrowing(pointcut = "(controllerPointcut() || servicePointcut() || repositoryPointcut()) && !apiDocPointcut() && !springDataRepositoryPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}, message = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                e.getCause() != null ? e.getCause() : "NULL",
                e.getMessage());
    }
}
