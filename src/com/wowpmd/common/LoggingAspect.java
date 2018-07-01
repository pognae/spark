package com.wowpmd.common;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

/**
 *
 * 클래스명: <code>LoggingAspect</code>
 * 
 * <pre>
 * 모든서비스에서 체크하는 AOP
 * 각 서비스에서의 성능을 체크한다.
 * </pre>
 *
 * @date 2010. 8. 11.
 * @author 이경연
 *
*/
public class LoggingAspect {
    
    private final Log log = LogFactory.getLog(LoggingAspect.class);
    
    @SuppressWarnings("serial")
    private Set<String> except = new HashSet<String>() {
        {
            add("SystemService.getCodeHandler()");
            add("MessageService.getMessageHandler()");
            add("SystemService.getNetAddress()");
        }
    };

    public Object serviceChecker(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getName();
        String serviceName = className.substring(className.lastIndexOf(".") + 1, className.length());
        String methodName = joinPoint.getSignature().getName();
//        Object[] arguments = joinPoint.getArgs();


        StopWatch stopWatch = new StopWatch();
        String serviceMethodName = serviceName + "." + methodName + "()";
        boolean isBatch = StringUtils.startsWith(serviceMethodName, "BatchService");
        boolean isSystemCode = this.except.contains(serviceMethodName);
        if (!isBatch && !isSystemCode && log.isDebugEnabled()) {
            log.debug("### " + serviceMethodName + ": 메소드시작");
//            log.debug(" [Arguments]" + Arrays.toString(arguments));
        }
        
        try {
            if (!isSystemCode) {
                stopWatch.start();
            }
            
            Object retValue = joinPoint.proceed();
            return retValue;
        } finally {
            if (!isSystemCode) {
                stopWatch.stop();
                
                if (!isBatch && log.isDebugEnabled()) {
                    log.debug("### " + serviceMethodName + ": 메소드종료(runtime: " + stopWatch.getTotalTimeMillis() + ")");
                }
                
                if (!isBatch && stopWatch.getTotalTimeMillis() > 5000) {
                    if (log.isInfoEnabled()) {
                        log.info(String.format("서비스 성능에 문제가있습니다.[%s][%dms]", serviceMethodName, stopWatch.getTotalTimeMillis()));
                    }
                }
            }
        }
    }
}
