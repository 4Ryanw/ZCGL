package cn.cqu.dao;

import cn.cqu.pojo.DeviceType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
     * 查询所有设备品牌
     * @return
     */
    @Select("select * from t_device_brand")
    List<DeviceType> listDeviceBrand();

    @Insert("insert into t_device_type values(UUID(),#{type})")
    int insertDeviceType(DeviceType deviceType);
}
