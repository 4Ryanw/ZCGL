package cn.cqu.controller;

import cn.cqu.pojo.DeviceBrand;
import cn.cqu.pojo.DeviceType;
import cn.cqu.service.BaseInfoService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/baseInfos")
public class BaseInfoController {

    @Autowired
    private BaseInfoService baseInfoService;

    /**
     * 获取所有设备类型信息
     * @param map
     * @return
     */
    @RequestMapping("/type/list")
    public String listDeviceType(ModelMap map){
        List<DeviceType> typeList = baseInfoService.listDeviceType();
        map.put("typeList",typeList);
     return "deviceTypes::table-refresh";
    }

    /**
     * 添加设备类型
     * @param deviceType
     * @return
     */
    @PostMapping("/type")
    @ResponseBody
    public int addtDeviceType(DeviceType deviceType){
        return baseInfoService.addtDeviceType(deviceType);
    }

    /**
     * 删除设备类型
     * @param typeId
     * @return
     */
    @DeleteMapping("/type/{typeId}")
    @ResponseBody
    public int delteDeviceTypByid(@PathVariable("typeId") String typeId){
        return baseInfoService.deleteDeviceTypeById(typeId);
    }

    /**
     * 修改类型
     * @param deviceType
     * @return
     */
    @PutMapping("/type")
    @ResponseBody
    public int updateDeviceType(DeviceType deviceType){
        return baseInfoService.updateDeviceType(deviceType);
    }

    /**
     * 获取所有设备品牌信息
     * @param map
     * @return
     */
    @RequestMapping("/brand/list")
    public String listDeviceBrand(ModelMap map){
        List<DeviceBrand> brandList = baseInfoService.listDeviceBrand();
        map.put("brandList",brandList);
        return "deviceBrands::table-refresh";
    }

    /**
     * 添加设备品牌
     * @param deviceBrand
     * @return
     */
    @PostMapping("/brand")
    @ResponseBody
    public int addtDeviceBrand(DeviceBrand deviceBrand){
        return baseInfoService.addtDeviceBrand(deviceBrand);
    }

    /**
     * 删除设备品牌
     * @param brandId
     * @return
     */
    @DeleteMapping("/brand/{brandId}")
    @ResponseBody
    public int delteDeviceBrandByid(@PathVariable("brandId") String brandId){
        return baseInfoService.deleteDeviceBrandById(brandId);
    }

    /**
     * 修改品牌
     * @param deviceBrand
     * @return
     */
    @PutMapping("/brand")
    @ResponseBody
    public int updateDeviceBrand(DeviceBrand deviceBrand){
        return baseInfoService.updateDeviceBrand(deviceBrand);
    }

}
