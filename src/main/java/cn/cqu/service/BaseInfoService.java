package cn.cqu.service;


import cn.cqu.dao.BaseInfoDao;
import cn.cqu.pojo.DeviceBrand;
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

    /**
     * 删除设备类型
     * @param typeId
     * @return
     */
    int deleteDeviceTypeById(String typeId);

    /**
     * 更新设备类型
     * @param deviceType
     * @return
     */
    int updateDeviceType(DeviceType deviceType);


    /**
     * 获取所有设备品牌
     * @return
     */
    List<DeviceBrand> listDeviceBrand();


    /**
     * 添加设备品牌
     * @param deviceBrand
     * @return
     */
    int addtDeviceBrand(DeviceBrand deviceBrand);

    /**
     * 删除设备品牌
     * @param brandId
     * @return
     */
    int deleteDeviceBrandById(String brandId);

    /**
     * 更新设备品牌
     * @param deviceBrand
     * @return
     */
    int updateDeviceBrand(DeviceBrand deviceBrand);

}
