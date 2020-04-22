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
import java.util.Date;
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
        DeviceDTO example = new DeviceDTO();
        example.setDevId(deviceId);
        List<DeviceDTO> deviceDTOList = deviceDao.listDeviceDtoByExample(example);
        if(deviceDTOList.size()>0){
            DeviceDTO deviceDTO = deviceDTOList.get(0);
            deviceDTO.setUserList(accountDao.listUserByDeviceId(deviceId));
            return deviceDTO;
        }
        else return null;

    }

    /**
     * 新增设备
     *
     * @param example
     * @return
     */
    @Override
    public int addDevice(Device example) {
        return deviceDao.insertDevice(example);
    }

    /**
     * 修改设备信息
     *
     * @param example
     * @return
     */
    @Override
    public int updateDevice(Device example) {
        example.setLastUpate(new Date());
        return deviceDao.updateDevice(example);
    }

    /**
     * 根据Id删除设备
     *
     * @param devId
     * @return
     */
    @Override
    public int deleteDeviceById(String devId) {
        return deviceDao.deleteDeviceById(devId);
    }

    /**
     * 修改设备状态
     *
     * @param devId
     * @param status
     * @return
     */
    @Override
    public int updateStatusByid(String devId, int status) {
        DeviceUseage deviceUseage = new DeviceUseage();
        deviceUseage.setDevId(devId);
        deviceUseage.setDevStatus(status);
        return deviceDao.updateDeviceUseage(deviceUseage);
    }
}
