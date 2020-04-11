package cn.cqu.service;


import cn.cqu.pojo.Organization;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 组织service接口
 */
public interface OrganizationService {


    /**
     * 根据组织id查询
     * @param orgId
     * @return
     */
    Organization  getOrganizationByid(String orgId);


    /**
     * 根据父级组织id查询
     * @param parentId
     * @return
     */
    List<Organization> listOrganizationByParentId(String parentId);


    /**
     * 根据条件动态查询
     * @param example
     * @return
     */
    List<Organization> listOrganizationByExample(Organization example);

    /**
     * 查询物理位置
     * @param fristid
     * @param secondId
     * @return
     */
    List<Organization> listMacaddressByRootID(String fristid,String secondId);


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
