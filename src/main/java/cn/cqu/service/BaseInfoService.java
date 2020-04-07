package cn.cqu.service;


import cn.cqu.dao.BaseInfoDao;
import cn.cqu.pojo.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 基础信息service接口
 */
public interface BaseInfoService {

    /**
     * 获取所有设备类型
     * @return
     */
    List<DeviceType> listDeviceType();


    /**
     * 添加设备类型
     * @param deviceType
     * @return
     */
    int addtDeviceType(DeviceType deviceType);

}
