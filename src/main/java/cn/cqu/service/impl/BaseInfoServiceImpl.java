package cn.cqu.service.impl;

import cn.cqu.dao.BaseInfoDao;
import cn.cqu.pojo.DeviceBrand;
import cn.cqu.pojo.DeviceType;
import cn.cqu.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseInfoServiceImpl implements BaseInfoService {
    @Autowired
    private BaseInfoDao baseInfoDao;

    /**
     * 获取所有设备类型
     * @return
     */
    @Override
    public List<DeviceType> listDeviceType() {
        return baseInfoDao.listDeviceType();
    }

    /**
     * 添加设备类型
     *
     * @param deviceType
     * @return
     */
    @Override
    public int addtDeviceType(DeviceType deviceType) {
        if(null == baseInfoDao.getDeviceTypeByName(deviceType.getType())){//先查询是否有同名类型
            return baseInfoDao.insertDeviceType(deviceType);
        }else {
            return -1;
        }
    }

    /**
     * 删除设备类型
     *
     * @param typeId
     * @return
     */
    @Override
    public int deleteDeviceTypeById(String typeId) {
        return baseInfoDao.deleteDeviceTypeById(typeId);
    }

    /**
     * 更新设备类型
     *
     * @param deviceType
     * @return
     */
    @Override
    public int updateDeviceType(DeviceType deviceType) {
        if(null == baseInfoDao.getDeviceTypeByName(deviceType.getType())){//先查询是否有同名类型
            return baseInfoDao.updateDevicType(deviceType);
        }else {
            return -1;
        }

    }

    /**
     * 获取所有设备品牌
     *
     * @return
     */
    @Override
    public List<DeviceBrand> listDeviceBrand() {
        return baseInfoDao.listDeviceBrand();
    }

    /**
     * 添加设备品牌
     *
     * @param deviceBrand
     * @return
     */
    @Override
    public int addtDeviceBrand(DeviceBrand deviceBrand) {
        if(null == baseInfoDao.getDeviceBrandByName(deviceBrand.getBrand())){//先查询是否有同名品牌
            return baseInfoDao.insertDeviceBrand(deviceBrand);
        }else {
            return -1;
        }
    }

    /**
     * 删除设备品牌
     *
     * @param brandId
     * @return
     */
    @Override
    public int deleteDeviceBrandById(String brandId) {
        return baseInfoDao.deleteDeviceBrandById(brandId);
    }

    /**
     * 更新设备品牌
     *
     * @param deviceBrand
     * @return
     */
    @Override
    public int updateDeviceBrand(DeviceBrand deviceBrand) {
        if(null == baseInfoDao.getDeviceTypeByName(deviceBrand.getBrand())){//先查询是否有同名类型
            return baseInfoDao.updateDevicBrand(deviceBrand);
        }else {
            return -1;
        }
    }
}
