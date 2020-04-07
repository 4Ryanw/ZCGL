package cn.cqu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/device")
public class DeviceController {
    @RequestMapping("/manageInfo")
    public String manageInfo(){
        System.out.println("访问设备台账");
     return "deviceManage";
    }

    @RequestMapping("/list")
    public String index(){
        System.out.println("访问设备管理");
        return "/deviceList";
    }
}
