package cn.cqu.util;

import cn.cqu.dao.SystemLogDao;
import cn.cqu.pojo.SystemLog;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@Aspect
@Component
public class ControllerAspect  {
    @Autowired
    SystemLogDao systemLogDao;
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    //切入点表达式

//    @Pointcut("execution(public int cn.cqu.service.impl.*.*(..))")

    @Pointcut("@annotation(cn.cqu.util.MyLog)")
    public void pointCut() {
    }

    private String requestPath = null ; // 请求地址
    private String userNo = null ; // 用户名
    private Map<?,?> inputParamMap = null ; // 传入参数
    private Map<String, Object> outputParamMap = null; // 存放输出结果
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间
    private String remoteAddr;          //请求IP
    private String discription; //方法描述


    /**
     * 记录开始时间
     * @param joinPoint
     */
    @Before("pointCut()")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
    }

    /**
     * 方法调用后触发
     * @param joinPoint
     * @throws IOException
     */
    @After("pointCut()")
    public void doAfterInServiceLayer(JoinPoint joinPoint) throws IOException {
        endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
        discription = getControllerMethodDescription2(joinPoint);
        if(StringUtils.isBlank(discription)){
            discription = "-";
        }
        this.printOptLog();
    }


    /**
     * 环绕触发
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        /**
         * 1.获取request信息
         * 2.根据request获取session
         * 3.从session中取出登录用户信息
         */
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        userNo = (String) SecurityUtils.getSubject().getPrincipal();//管理员id

        remoteAddr = request.getRemoteAddr();//请求的IP

        if (StringUtils.isBlank(remoteAddr)) {
            remoteAddr = "-";
        }
        // 获取输入参数
        inputParamMap = request.getParameterMap();
        // 获取请求地址
        requestPath = request.getRequestURI();
        if (StringUtils.isBlank(requestPath)) {
            requestPath = "-";
        }
        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
        outputParamMap = new HashMap<String, Object>();
        Object result = pjp.proceed();// result的值就是被拦截方法的返回值
        outputParamMap.put("result", result);

        return result;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return discription
     */
    public static String getControllerMethodDescription2(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyLog controllerLog = method
                .getAnnotation(MyLog.class);
        String discription = controllerLog.actionName();
        return discription;
    }

    /**
     * 输出日志
     */
    private void printOptLog() {
        Gson gson = new Gson();
        String optTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
        String data1 = "管理员：" + userNo + "\r\n"
                + "请求地址:" + requestPath + "\r\n"
                + "请求IP:" + remoteAddr + "\r\n"
                + "方法描述:" + discription + "\r\n"
                + "开始时间:" + optTime + "\r\n"
                + "调用方法时间:" + (endTimeMillis - startTimeMillis) + "ms;" + "\r\n"
          +"参数:"+gson.toJson(inputParamMap)+"\r\n"
         +"返回参数:"+gson.toJson(outputParamMap)+"\r\n";
        System.out.println(data1);

        SystemLog systemLog = new SystemLog();
        systemLog.setUserNo(userNo);
        systemLog.setRequestPath(requestPath);
        systemLog.setRemoteAddr(remoteAddr);
        systemLog.setDiscription(discription);
        systemLog.setStartTime(new Date(startTimeMillis));
        systemLog.setExecutionTimeMillis(endTimeMillis - startTimeMillis);
        systemLog.setInputParam(gson.toJson(inputParamMap));
        systemLog.setOutputParam(gson.toJson(outputParamMap));
        systemLogDao.insertLog(systemLog);
    }




}
