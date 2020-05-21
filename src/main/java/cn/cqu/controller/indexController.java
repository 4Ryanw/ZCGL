package cn.cqu.controller;

import cn.cqu.dao.BaseInfoDao;
import cn.cqu.pojo.DeviceBrand;
import cn.cqu.pojo.DeviceType;
import cn.cqu.pojo.Notice;
import cn.cqu.pojo.Organization;
import cn.cqu.pojo.dto.DeviceDTO;
import cn.cqu.service.BaseInfoService;
import cn.cqu.service.DeviceService;
import cn.cqu.service.NoticeService;
import cn.cqu.service.OrganizationService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 页面跳转管理控制器
 */
@Controller
public class indexController {
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private BaseInfoService baseInfoService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private NoticeService noticeService;

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
    public void index(ModelMap map){
        List<Notice> list = noticeService.listAll();
        if(list.size()>3){
            list = list.subList(0,3);
        }
        map.put("noticeList",list);
    }
    //设备管理
    @GetMapping("/deviceManage")
    public void deviceManage(HttpServletRequest request,ModelMap map){
        String user = (String) SecurityUtils.getSubject().getPrincipal();
        request.getSession().setAttribute("loginUser",user);
        List<DeviceType> typeList = baseInfoService.listDeviceType();
        List<DeviceBrand> deviceBrandList =  baseInfoService.listDeviceBrand();
        map.put("deviceBrandList",deviceBrandList);
        map.put("deviceTypeList",typeList);
    }
    //账户信息
    @GetMapping("/account")
    public void account(){}
    //设备台账
    @GetMapping("/deviceList")
    public void deviceList(ModelMap map){
        List<DeviceType> typeList = baseInfoService.listDeviceType();
        List<DeviceBrand> deviceBrandList =  baseInfoService.listDeviceBrand();
        Organization example = new Organization();
        example.setOrgLevel(1);
        List<Organization> organizationList = organizationService.listOrganizationByExample(example);
        map.put("parentOrg",organizationList);
        map.put("deviceBrandList",deviceBrandList);
        map.put("deviceTypeList",typeList);
    }
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
    //新增设备
    @GetMapping("/addDevice")
    public void addDevice(ModelMap map){
          List<DeviceBrand> deviceBrandList =  baseInfoService.listDeviceBrand();
          map.put("deviceBrandList",deviceBrandList);
          List<DeviceType> deviceTypeList =  baseInfoService.listDeviceType();
          map.put("deviceTypeList",deviceTypeList);
    }
    //编辑设备
    @GetMapping("/editDevice/{devId}")
    public String editDevice(ModelMap map,@PathVariable String devId){
          DeviceDTO deviceDTO = deviceService.getDeviceDtoById(devId);
          map.put("deviceDTO",deviceDTO);
          List<DeviceBrand> deviceBrandList =  baseInfoService.listDeviceBrand();
          map.put("deviceBrandList",deviceBrandList);
          List<DeviceType> deviceTypeList =  baseInfoService.listDeviceType();
          map.put("deviceTypeList",deviceTypeList);
          return "editDevice";
    }

    /**
     * 分配设备
     * @param map
     * @param devId
     * @return
     */
    @GetMapping("/allotDevice/{devId}")
    public String allotDevice(ModelMap map,@PathVariable String devId){
        DeviceDTO deviceDTO = deviceService.getDeviceDtoById(devId);
        map.put("deviceDTO",deviceDTO);

        Organization example = new Organization();
        example.setOrgLevel(1);
        List<Organization> organizationList = organizationService.listOrganizationByExample(example);
        map.put("parentOrg",organizationList);

        return "allotDevice";
    }

    /**
     * 编辑公告
     */
    @GetMapping("/noticeEdit")
    public void noticeEdit(){

    }
    //系统管理
    @GetMapping("/system")
    public void system(){}

}
