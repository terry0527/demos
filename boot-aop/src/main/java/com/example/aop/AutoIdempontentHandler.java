package com.example.aop;

import com.example.annotation.AutoIdempotent;
import com.example.service.RedisService;
import com.example.xss.IdempotenceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class AutoIdempontentHandler {

    @Autowired
    private RedisService redisService;

    @Pointcut("@annotation(com.example.annotation.AutoIdempotent)")
    public void autoIdempontentHandler() {

    }

//    @Before("autoIdempontentHandler()")
//    public void doBefore() throws Throwable {
//        log.info("idempontentHandler..doBefore()");
//    }

    /**
     * 请求头有token校验幂等性
     * @param joinPoint
     */
    @Before("autoIdempontentHandler()")
    public void before(JoinPoint joinPoint) {
        //拿到当前请求request
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (!redisService.checkToken(request)){
            throw new IdempotenceException("请求重复处理"); //没有检验通过直接抛出异常
        }

    }

    @Around("autoIdempontentHandler()")
    public Object doAround(ProceedingJoinPoint joinpoint) throws Throwable {

        //第二种校验方法
        boolean checkres = this.handleRequest(joinpoint);
        if(checkres){
            //重复请求,提示重复 报错
            log.info("重复性请求..");
            throw new IdempotenceException("请求重复处理");
        }
        return joinpoint.proceed();
    }

    private Boolean handleRequest(ProceedingJoinPoint joinpoint) {
        Boolean result = false;
        log.info("========判断是否是重复请求=======");
        MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
        //获取自定义注解值
        AutoIdempotent autoIdempotent = methodSignature.getMethod().getDeclaredAnnotation(AutoIdempotent.class);
        long expireTime = autoIdempotent.expireTime();
        // 获取参数名称
        String methodsName = methodSignature.getMethod().getName();
        String[] params = methodSignature.getParameterNames();
        //获取参数值
        Object[] args = joinpoint.getArgs();
        Map<String, Object> reqMaps = new HashMap<>();
        for(int i=0; i<params.length; i++){
            reqMaps.put(params[i], args[i]);
        }
//        String reqJSON = gson.toJson(reqMaps);
//        result = tokenService.checkRequest("user1", methodsName, expireTime, reqJSON, excludeKey);
        //判读redis中的数据是否存在
        //result = true;
        return result;
    }

    @AfterReturning(returning = "retVal", pointcut = "autoIdempontentHandler()")
    public void doAfter(Object retVal) throws Throwable {
        log.debug("{}", retVal);
    }
}
