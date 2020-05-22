package cn.cqu.service;


import cn.cqu.pojo.Device;
import cn.cqu.pojo.dto.DeviceDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据Id删除设备
     * @param devId
     * @return
     */
    int deleteDeviceById(String devId);

    /**
     * 修改设备状态
     * @param devId
     * @param status
     * @return
     */
    int updateStatusByid(String devId, int status);

    /**
     * 更新设备持有者
     * @param devId
     * @param groups
     * @return
     */
    int updateDevOwnersByDevId(String devId,String[] groups);

    /**
     * 更新设备部门
     * @param devId
     * @param fri_org
     * @param sec_org
     * @param orgid_addr
     * @return
     */
    int updateDevOrgsByDevId(String devId,String fri_org,String sec_org,String orgid_addr );

    /**
     * 根据条件动态查询设备
     * @param example
     * @return
     */
    List<DeviceDTO> listDeviceDTOByexample(DeviceDTO example);

    /**
     * 导出Excel
     * @param response
     * @param pageName
     */
    void exportExcel(HttpServletResponse response, String pageName,String[] headers);

    /**
     * 按用户名筛选设备
     * @param dataList
     * @param userName
     * @return
     */
     List<DeviceDTO> selectDeviceDTObyUserName(List<DeviceDTO> dataList,String userName);

    /**
     * 根据类型统计设备数量
     * @param monthStr
     * @return
     */
    Map staDeviceByType(String monthStr);
    /**
     * 根据品牌统计设备数量
     * @param monthStr
     * @return
     */
    Map staDeviceByBrand(String monthStr);
    /**
     * 根据部门统计设备数量
     * @param monthStr
     * @return
     */
    Map staDeviceByOrg(String monthStr);

}
