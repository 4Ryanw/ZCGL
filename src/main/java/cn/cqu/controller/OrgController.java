package cn.cqu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 组织类型控制器
 */

@Controller
@RequestMapping("/organizations")
public class OrgController {
    @RequestMapping("/list")
    public String listOrganizations(){
        System.out.println("访问组织结构");
        return "/Organizations";
    }
    @RequestMapping("/fri_Department/list")
    public String listSuperiorDepartment(){
        System.out.println("访问一级部门");
        return "/friDepartments";
    }
    @RequestMapping("/sec_Department/list")
    public String listUserInfo(){
        System.out.println("访问二级部门");
        return "/secDepartments";
    }
    @RequestMapping("/macAddress/list")
    public String manageInfo(){
        System.out.println("访问物理位置");
        return "/macAddress";
    }

}
