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
}
