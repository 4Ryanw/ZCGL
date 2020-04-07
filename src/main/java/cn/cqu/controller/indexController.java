package cn.cqu.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面跳转管理控制器
 */
@Controller
public class indexController {
    /**
     *  跳转主页
     */
    @GetMapping("/home")
    public String home(ModelMap map){
        String user = (String) SecurityUtils.getSubject().getPrincipal();
        map.addAttribute("loginUser",user);
        return "home";
    }
    //首页 (欢迎页面)
    @GetMapping("/index")
    public void index(){}
    //设备管理
    @GetMapping("/deviceManage")
    public void deviceManage(){}
    //账户信息
    @GetMapping("/account")
    public void account(){}
    //设备台账
    @GetMapping("/deviceList")
    public void deviceList(){}
    //信息公告
    @GetMapping("/notice")
    public void noticeList(){}
    //设备类型
    @GetMapping("/deviceTypes")
    public void deviceTypes(){}
    //设备品牌
    @GetMapping("/deviceBrands")
    public void deviceBrands(){}
    //一级部门
    @GetMapping("/friDepartments")
    public void friDepartments(){}
    //二级部门
    @GetMapping("/secDepartments")
    public void secDepartments(){}
    //物理地址
    @GetMapping("/macAddress")
    public void macAddress(){}
    //组织结构
    @GetMapping("/Organizations")
    public void Organizations(){}
    //系统管理
    @GetMapping("/system")
    public void system(){}

}
