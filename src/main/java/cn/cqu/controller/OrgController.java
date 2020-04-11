package cn.cqu.controller;

import cn.cqu.pojo.Organization;
import cn.cqu.service.OrganizationService;
import org.apache.ibatis.annotations.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织类型控制器
 */

@Controller
@RequestMapping("/organization")
public class OrgController {
    @Autowired
    private OrganizationService organizationService;

    /**
     * 根据条件动态查询组织，数据加载到表格
     */
    @PostMapping("/list")
    public String listOrganizationByLevel(Organization example, ModelMap map){
        List<Organization> list = organizationService.listOrganizationByExample(example);
        int sublevel = example.getOrgLevel();
        map.put("dtoList",list);
        String pageName = null;
        switch (sublevel) {  //根据参数确定返回页面目标
            case  1: pageName= "friDepartments" ;break;
            case  2: pageName= "secDepartments" ;break;
            case  3: pageName= "macAddress" ;break;
        }
        return  pageName+"::table-refresh";
    }

    /**
     * 根据条件动态查询子级组织
     */
    @PostMapping("/sub/list")
    @ResponseBody
    public List listOrganization(Organization example){
        List<Organization> list = organizationService.listOrganizationByExample(example);
        return  list;
    }

    /**
     * 根据一级部门和二级部门id查询物理位置
     */
    @PostMapping("/macAddress/list")
    public String listMacaddress(String fristId,String secondId,ModelMap map){
        List<Organization> list = organizationService.listMacaddressByRootID(fristId,secondId);
        map.put("dtoList",list);
        return  "macAddress::table-refresh";
    }



    /**
     * 添加组织
     * @param organization
     * @return
     */
    @PostMapping
    @ResponseBody
    public int addOrganization(Organization organization){
        return organizationService.insertOrganization(organization);
    }

    /**
     * 删除组织
     * @param orgId
     * @return
     */
    @DeleteMapping("/{orgId}")
    @ResponseBody
    public int delteOrganizationByid(@PathVariable("orgId") String orgId){
        return organizationService.deleteOrganizationById(orgId);
    }

    /**
     * 修改组织名称
     * @param organization
     * @return
     */
    @PutMapping
    @ResponseBody
    public int updateDeviceType(Organization organization){
        return organizationService.updateOrganizationName(organization);
    }




}
