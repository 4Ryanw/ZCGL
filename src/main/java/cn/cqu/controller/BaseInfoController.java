package cn.cqu.controller;

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
     * 获取所有设备品牌
     * @return
     */
    @GetMapping("/brand/list")
    public String listDeviceBrand(){
     return "/deviceBrands";
    }

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
}
