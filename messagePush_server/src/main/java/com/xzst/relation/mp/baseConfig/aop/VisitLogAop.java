package com.xzst.relation.mp.baseConfig.aop;


import com.xzst.relation.mp.annotation.VisitLog;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
public class VisitLogAop {
    private final Logger logger = Logger.getLogger(this.getClass());

    //定义切点
    @Pointcut("@annotation(com.xzst.relation.mp.annotation.VisitLog)")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        String className= VisitLogAop.getAnnotationLogForClass(joinPoint);
        String methodName=VisitLogAop.getAnnotationLogForMethod(joinPoint);
        logger.info(className);
        logger.info(methodName);

        ;


    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("方法的返回值 : " + ret);
    }

    //后置异常通知
    @AfterThrowing("logPointCut()")
    public void throwss(JoinPoint jp) {
        logger.info("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("logPointCut()")
    public void after(JoinPoint jp) {
        logger.info("方法最后执行.....");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("logPointCut()")
    public Object arround(ProceedingJoinPoint pjp) {
        logger.info("方法环绕start.....");
        try {
            Object o = pjp.proceed();
            logger.info("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getAnnotationLogForClass(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        Annotation annotation=signature.getDeclaringType().getAnnotation(VisitLog.class);
        String className;
        if(annotation==null){
            className=signature.getDeclaringTypeName();
            return className;
        }else{
            className=((VisitLog)annotation).value();
            return className;
        }
    }

    private static String getAnnotationLogForMethod(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String methodName;
        if (method != null) {
            methodName=method.getAnnotation(VisitLog.class).value();
            return methodName;
        }else{
            return method.getName();
        }
    }



}
