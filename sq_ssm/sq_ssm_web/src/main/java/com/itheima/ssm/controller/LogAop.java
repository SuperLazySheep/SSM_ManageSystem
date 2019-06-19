package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Syslog;
import com.itheima.ssm.service.ISyslogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISyslogService syslogService;

    private Date visitTime; //访问时间
    private Class clazz; // 访问的类
    private Method method; // 访问的方法

    //获取访问的时间，访问的那个类，访问的那个方法
    //前置通知
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime = new Date();// 当前时间就是访问时间
        clazz  = joinPoint.getTarget().getClass(); //获取取当前类
        String methodName = joinPoint.getSignature().getName(); // 获取当前方法的名称
        Object[] args = joinPoint.getArgs(); // 获取当前方法额参数
        if (args == null || args.length==0){
            //无参数
            method = clazz.getMethod(methodName);
        }else{
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for(int i =0; i < args.length; i++){
                classArgs[i] = args[i] .getClass() ;
            }
            clazz.getMethod(methodName,classArgs); //获取有参数方法
        }
    }

    /**
     *获取访问的时长
     *          url
     *          ip
     *          用户
     * @param joinPoint
     */
    //后置通知
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        Long time = new Date().getTime() - visitTime.getTime(); //获取访问时长

        String url = "";
        //获取url
        if(clazz !=null && method !=null && clazz != LogAop.class){
            //1，获取类上的@RequestMapping("/...")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if( classAnnotation != null){
                String[] classValue = classAnnotation.value();

                //2，获取方法上的@RequestMapping
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();

                    url = classValue[0]+methodValue[0];

                    // 获取ip
                    String ip = request.getRemoteAddr();
                    //获取当前用户
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    User user = (User) securityContext.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志封装到Syslog对象中
                    Syslog syslog = new Syslog();
                    syslog.setIp(ip);
                    syslog.setUrl(url);
                    syslog.setUsername(username);
                    syslog.setVisitTime(visitTime);
                    syslog.setExecutionTime(time);
                    syslog.setMethod("[类名]:"+clazz.getName()+"[方法名]："+method.getName());

                    // 调用Service，调用dao将sysLog insert数据库
                    syslogService.save(syslog);
                }
            }
        }
    }
}
