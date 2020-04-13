package cn.cqu.dao;

import cn.cqu.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 设备Dao类
 */
@Repository
public interface DeviceDao {

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
 @Insert("insert into t_device values (UUID(),#{typeId},#{devModel},#{brandId},#{devModel},#{purchaseTime},#{lastUpate})")
 int insertDevice(Device device);
 /**
  * 插入设备使用信息
  * @param deviceUseage
  * @return
  */
 @Insert("insert into t_device values (#{devId},#{devStatus},#{domainStatus},#{depFri},#{depSec},#{depThir},#{network},#{address})")
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
}
