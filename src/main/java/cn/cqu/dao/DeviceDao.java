package cn.cqu.dao;

import cn.cqu.pojo.*;
import cn.cqu.pojo.dto.DeviceDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 设备Dao类
 */
@Repository
public interface DeviceDao {


 /**
  * 按条件动态查询DTO
  * @param example
  * @return
  */
 List<DeviceDTO> listDeviceDtoByExample(DeviceDTO example);

 /**
  * 按id查询设备基础信息
  * @param devId
  * @return
  */
 @Select("select * from t_device where dev_id = #{devId}")
  Device getDeviceById(String devId);
 /**
  * 查询所有设备基础信息
  * @return
  */
 @Select("select * from t_device")
 List<Device> listDevice();
 /**
  * 按id查询设备使用信息
  * @param devId
  * @return
  */
 @Select("select * from t_device_usage where dev_id = #{devId}")
 DeviceUseage getDeviceUseageById(String devId);

 /**
  * 按组织id查询设备
  * @param orgId
  * @return
  */
 List<Device> listDeviceByOrgId(String orgId);



 /**
  * 按id查询设备运行信息
  * @param devId
  * @return
  */
 @Select("select * from t_device_running where dev_id = #{devId}")
 DeviceRunning getDeviceRunningById(String devId);
 /**
  * 按id查询设备维护信息
  * @param devId
  * @return
  */
 @Select("select * from t_device_maintain where dev_id = #{devId}")
 DeviceMaintain getDeviceMaintainById(String devId);

 /**
  * 插入设备基础信息
  * @param device
  * @return
  */
 @Insert("insert into t_device values (#{devId},#{typeId},#{brandId},#{devModel},#{purchaseTime},#{erpCode},#{lastUpate})")
 int insertDevice(Device device);
 /**
  * 插入设备使用信息
  * @param deviceUseage
  * @return
  */
 @Insert("insert into t_device_usage values (#{devId},#{devStatus},#{domainStatus},#{depFri},#{depSec},#{depThir},#{network},#{address})")
 int insertDeviceUseage(DeviceUseage deviceUseage);
 /**
  * 插入设备运行信息
  * @param deviceRunning
  * @return
  */
 @Insert("insert into t_device values (#{devId},#{macAddress},#{ipAddress},#{systemVersion},#{hdSize},#{cpuInfo},#{memoryInfo})")
 int insertDeviceRunning(DeviceRunning deviceRunning);
 /**
  * 插入设备维护信息
  * @param deviceMaintain
  * @return
  */
 @Insert("insert into t_device values (#{devId},#{maintainInfo},#{maintainDate})")
 int insertDeviceMaintain(DeviceMaintain deviceMaintain);

    /**
     * 插入设备使用者信息
     * @param devId
     * @param userId
     * @return
     */
 @Insert("insert into t_device_user values(#{devId},#{userId})")
 int insertDeviceUser(@Param("devId") String devId, @Param("userId")String userId);

    /**
     * 删除设备使用者信息
     * @param userId
     * @param devId
     * @return
     */
 @Delete("delete from t_device_user where user_uuid = #{userId} and dev_id = #{devId}")
 int deleteDeviceUserById(@Param("devId") String devId, @Param("userId")String userId);

 /**
  * 修改设备基础信息
  * @param device
  * @return
  */
 @Update("update t_device set type_id =#{typeId},brand_id =#{brandId},dev_model=#{devModel},purchase_time=#{purchaseTime},erp_code=#{erpCode},last_Upate=#{lastUpate} where dev_id=#{devId}")
 int updateDevice(Device device);

 /**
  *修改设备使用信息
  * @param deviceUseage
  * @return
  */
 int updateDeviceUseage(DeviceUseage deviceUseage);

 /**
  * 根据id删除设备
  * @param devId
  * @return
  */
 @Delete("delete from t_device where dev_id = #{devId}")
 int deleteDeviceById(String devId);

 /**
  * 根据设备类型分类统计
  * @param monthStr
  * @return
  */
 List<HashMap<String,Object>> staDeviceByType(String monthStr);
 /**
  * 根据设备品牌分类统计
  * @param monthStr
  * @return
  */
 List<HashMap<String,Object>> staDeviceByBrand(String monthStr);
 /**
  * 根据分配部门分类统计
  * @param monthStr
  * @return
  */
 List<HashMap<String,Object>> staDeviceByOrg(String monthStr);
 /**
  * 根据设备状态分类统计
  * @param monthStr
  * @return
  */
 List<HashMap<String,Object>> staDeviceByStatus(String monthStr);

}
