package cn.cqu.dao;

import cn.cqu.pojo.DeviceBrand;
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
     * 根据id查询设备类型
     * @param typeId
     * @return
     */
    @Select("select * from t_device_type where type_id = #{typeId}")
    DeviceType getDeviceTypeById(String typeId);

    /**
     * 根据类型名查找设备类型
     * @return
     */
    @Select("select * from t_device_type where type = #{type}")
    DeviceType getDeviceTypeByName(String type);

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

    /**
     * 根据品牌名查找设备品牌
     * @return
     */
    @Select("select * from t_device_brand where brand = #{brand}")
    DeviceBrand getDeviceBrandByName(String brand);

    /**
     * 查询所有设备品牌
     * @return
     */
    @Select("select * from t_device_brand")
    List<DeviceBrand> listDeviceBrand();

    /**
     * 根据id查询设备品牌
     * @param brandId
     * @return
     */
    @Select("select * from t_device_brand where brand_id = #{brandId}")
    DeviceBrand getDeviceBrandById(String brandId);

    /**
     * 插入设备品牌
     * @param DeviceBrand
     * @return
     */
    @Insert("insert into t_device_brand values(UUID(),#{brand})")
    int insertDeviceBrand(DeviceBrand DeviceBrand);

    /**
     * 删除设备品牌
     * @param brandId
     * @return
     */
    @Delete("delete from t_device_brand where brand_id = #{brandId}")
    int deleteDeviceBrandById(String brandId);

    /**
     * 修改设备品牌
     * @param DeviceBrand
     * @return
     */
    @Update("update t_device_brand set brand = #{brand} where brand_id = #{brandId} ")
    int updateDevicBrand(DeviceBrand DeviceBrand);
}
