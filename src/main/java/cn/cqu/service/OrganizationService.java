package cn.cqu.service;


import cn.cqu.pojo.Organization;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 组织service接口
 */
public interface OrganizationService {

    /**
     * 按组织级别查询组织集合
     * @param level
     * @return
     */
    List<Organization> listOrganizationByLevel(int level);



    /**
     * 根据id删除组织
     * @param id
     * @return
     */
    int deleteOrganizationById(String id);


    /**
     * 修改组织名称
     * @param organization
     * @return
     */
    int updateOrganizationName(Organization organization);


    /**
     * 增加组织
     * @param organization
     * @return
     */
    int insertOrganization(Organization organization);
}
