package cn.cqu.service.impl;

import cn.cqu.dao.BaseInfoDao;
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
}
