package cn.cqu.dao;

import cn.cqu.pojo.DeviceType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *基础信息Dao类
 */
@Repository
public interface BaseInfoDao {
    /**
     * 查询所有设备类型
     * @return
     */
    @Select("select * from t_device_type")
    List<DeviceType> listDeviceType();

    /**
     * 根据类型名查找设备类型
     * @return
     */
    @Select("select * from t_device_type where type = #{type}")
    DeviceType getDeviceTypeByName(String type);
    /**
     * 查询所有设备品牌
     * @return
     */
    @Select("select * from t_device_brand")
    List<DeviceType> listDeviceBrand();

    /**
     * 插入设备类型
     * @param deviceType
     * @return
     */
    @Insert("insert into t_device_type values(UUID(),#{type})")
    int insertDeviceType(DeviceType deviceType);

    /**
     * 删除设备类型
     * @param typeId
     * @return
     */
    @Delete("delete from t_device_type where type_id = #{typeId}")
    int deleteDeviceTypeById(String typeId);

    /**
     * 修改设备类型
     * @param deviceType
     * @return
     */
    @Update("update t_device_type set type = #{type} where type_id = #{typeId} ")
    int updateDevicType(DeviceType deviceType);
}
