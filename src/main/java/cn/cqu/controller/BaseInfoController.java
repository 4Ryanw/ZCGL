package cn.cqu.controller;

import cn.cqu.pojo.DeviceType;
import cn.cqu.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/brand/list")
    public String listDeviceBrand(){
     return "/deviceBrands";
    }

}
