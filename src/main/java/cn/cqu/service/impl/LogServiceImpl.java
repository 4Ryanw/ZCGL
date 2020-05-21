package cn.cqu.service.impl;

import cn.cqu.dao.SystemLogDao;
import cn.cqu.pojo.SystemLog;
import cn.cqu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 日志服务实现类
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private SystemLogDao systemLogDao;

    /**
     * 根据时间范围查询日志
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<SystemLog> listLogsByDate(String startTime, String endTime) {
        return systemLogDao.listLogByDateStr(startTime,endTime);
    }
}
