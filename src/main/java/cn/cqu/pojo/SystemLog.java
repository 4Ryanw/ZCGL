package cn.cqu.pojo;

import java.io.Serializable;
import java.util.Date;

public class SystemLog implements Serializable {
    private String logId;
    private String userNo ; // 操作人员
    private String requestPath; // 请求地址
    private String remoteAddr;          //请求IP
    private String discription; //方法描述
    private Date startTime; // 开始时间
    private long executionTimeMillis = 0; // 方法调用时间
    private String inputParam; // 传入参数
    private String outputParam; // 输出结果

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public long getExecutionTimeMillis() {
        return executionTimeMillis;
    }

    public void setExecutionTimeMillis(long executionTimeMillis) {
        this.executionTimeMillis = executionTimeMillis;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    public String getOutputParam() {
        return outputParam;
    }

    public void setOutputParam(String outputParam) {
        this.outputParam = outputParam;
    }
}
