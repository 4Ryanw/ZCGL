package cn.cqu.service;


import cn.cqu.pojo.Device;
import cn.cqu.pojo.dto.DeviceDTO;

import java.util.List;

/**
 * 设备service接口
 */
public interface DeviceService {

    /**
     * 查询所有设备DTO
     * @return
     */
    List<DeviceDTO> listDeviceDto();

    /**
     * 根据id查询设备DTO
     * @param deviceId
     * @return
     */
    DeviceDTO getDeviceDtoById(String deviceId);

    /**
     * 新增设备
     * @param example
     * @return
     */
    int addDevice(Device example);


    /**
     * 修改设备信息
     * @param example
     * @return
     */
    int updateDevice(Device example);

}
