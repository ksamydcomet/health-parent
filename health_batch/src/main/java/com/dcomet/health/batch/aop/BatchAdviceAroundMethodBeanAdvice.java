package com.dcomet.health.batch.aop;

import java.util.Arrays;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.dcomet.fw.exception.DcometBatchException;

/**
 * central interface by which manipulation of Users occurs
 *
 * @author John
 */
@Component("batchAdviceAroundMethodBeanAdvice")
public class BatchAdviceAroundMethodBeanAdvice implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(BatchAdviceAroundMethodBeanAdvice.class);
    private static final long TRANSACTION_DELEY = 2000L;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        logger.debug("Method name : {}, Method arguments: {} ",
                methodInvocation.getMethod().getName(), Arrays.toString(methodInvocation.getArguments()));

        try {
            Thread.sleep(TRANSACTION_DELEY);
            // proceed to original method call
            Object result = methodInvocation.proceed();
            return result;

        } catch (IllegalArgumentException e) {
            logger.error("Method name :{}, Method arguments :{} Exception Message: {} Exception: {}",
                    methodInvocation.getMethod().getName(), Arrays.toString(methodInvocation.getArguments()), e.getMessage(), e);
            throw new DcometBatchException("Method name :"
                    + methodInvocation.getMethod().getName()
                    + ", Method arguments : "
                    + Arrays.toString(methodInvocation.getArguments()) + e);
        } catch (DcometBatchException e) {
            logger.error("Method name :{}, Method arguments :{} Exception Message: {} Exception: {}",
                    methodInvocation.getMethod().getName(), Arrays.toString(methodInvocation.getArguments()), e.getMessage(), e);
            throw new DcometBatchException("Method name :"
                    + methodInvocation.getMethod().getName()
                    + ", Method arguments : "
                    + Arrays.toString(methodInvocation.getArguments()) + e);
        } catch (Exception e) {
            logger.error("Method name :{}, Method arguments :{} Exception Message: {} Exception: {}",
                    methodInvocation.getMethod().getName(), Arrays.toString(methodInvocation.getArguments()), e.getMessage(), e);
            throw new DcometBatchException("Method name :"
                    + methodInvocation.getMethod().getName()
                    + ", Method arguments : "
                    + Arrays.toString(methodInvocation.getArguments()) + e);
        }
    }
}
