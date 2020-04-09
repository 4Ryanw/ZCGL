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
     * 根据组织等级查询组织
     */
    @GetMapping("/list/level/{level}")
    public String listOrganizationByLevel(@PathVariable("level") int level, ModelMap map){
        List<Organization> list = organizationService.listOrganizationByLevel(level);
        map.put("dtoList",list);
        String pageName = null;
        switch (level) {  //根据参数确定返回页面目标
            case  1: pageName= "friDepartments" ;break;
            case  2: pageName= "secDepartments" ;break;
            case  3: pageName= "macAddress" ;break;
        }
        return  pageName+"::table-refresh";
    }


    /**
     * 添加组织
     * @param organization
     * @return
     */
    @PostMapping()
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
