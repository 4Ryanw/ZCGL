package cn.cqu.controller;

import cn.cqu.pojo.Organization;
import cn.cqu.service.OrganizationService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 页面跳转管理控制器
 */
@Controller
public class indexController {
    @Autowired
    private OrganizationService organizationService;

    /**
     *  跳转主页
     */
    @GetMapping("/home")
    public String home(HttpServletRequest request){
        String user = (String) SecurityUtils.getSubject().getPrincipal();
        request.getSession().setAttribute("loginUser",user);
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
    public void secDepartments(ModelMap map){
        Organization example = new Organization();
        example.setOrgLevel(1);
        List<Organization> organizationList = organizationService.listOrganizationByExample(example);
        map.put("parentOrg",organizationList);
    }
    //物理地址
    @GetMapping("/macAddress")
    public void macAddress(ModelMap map){
        Organization example = new Organization();
        example.setOrgLevel(1);
        List<Organization> organizationList = organizationService.listOrganizationByExample(example);
        map.put("parentOrg",organizationList);
    }
    //组织结构
    @GetMapping("/Organizations")
    public void Organizations(){}
    //系统管理
    @GetMapping("/system")
    public void system(){}

}
