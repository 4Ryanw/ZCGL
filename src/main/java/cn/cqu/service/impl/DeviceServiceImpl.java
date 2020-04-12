package cn.cqu.service.impl;

import cn.cqu.dao.AccountDao;
import cn.cqu.dao.BaseInfoDao;
import cn.cqu.dao.DeviceDao;
import cn.cqu.dao.OrganizationDao;
import cn.cqu.pojo.Device;
import cn.cqu.pojo.DeviceUseage;
import cn.cqu.pojo.dto.DeviceDTO;
import cn.cqu.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private BaseInfoDao baseInfoDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private OrganizationDao organizationDao;
    /**
     * 查询所有设备DTO
     *
     * @return
     */
    @Override
    public List<DeviceDTO> listDeviceDto() {
        List<DeviceDTO> resList = new ArrayList<>();
        //获取所有设备基础信息
        List<Device> deviceList = deviceDao.listDevice();
        for (Device device:deviceList
             ) {
            DeviceDTO deviceDTO = getDeviceDtoById(device.getDevId());
            resList.add(deviceDTO);
        }
        return resList;
    }

    /**
     * 根据id查询设备DTO
     *
     * @param deviceId
     * @return
     */
    @Override
    public DeviceDTO getDeviceDtoById(String deviceId) {
        DeviceDTO deviceDTO = new DeviceDTO();
        Device device = deviceDao.getDeviceById(deviceId);
        deviceDTO.setDevice(device);
        DeviceUseage deviceUseage = deviceDao.getDeviceUseageById(deviceId);
        deviceDTO.setDeviceUseage(deviceUseage);
        deviceDTO.setDeviceRunning(deviceDao.getDeviceRunningById(deviceId));
        deviceDTO.setDeviceMaintain(deviceDao.getDeviceMaintainById(deviceId));
        deviceDTO.setDeviceBrand(baseInfoDao.getDeviceBrandById(device.getBrandId()).getBrand());
        deviceDTO.setDeviceTpe(baseInfoDao.getDeviceTypeById(device.getTypeId()).getType());
        deviceDTO.setDep_fri(organizationDao.getOrganizationById(deviceUseage.getDepFri()).getOrgName());
        deviceDTO.setDep_sec(organizationDao.getOrganizationById(deviceUseage.getDepSec()).getOrgName());
        deviceDTO.setDep_mac(organizationDao.getOrganizationById(deviceUseage.getAddress()).getOrgName());
        deviceDTO.setUserList(deviceDao.listUserByDeviceId(deviceId));
        return deviceDTO;
    }
}
