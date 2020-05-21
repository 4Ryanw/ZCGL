package cn.cqu.service;


import cn.cqu.pojo.SystemLog;

import java.util.Date;
import java.util.List;

/**
 * 日志service接口
 */
public interface LogService {

    /**
     * 根据时间范围查询日志
     * @param startTime
     * @param endTime
     * @return
     */
    List<SystemLog> listLogsByDate(String startTime,String endTime);


}
