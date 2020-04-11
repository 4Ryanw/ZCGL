package cn.cqu.service.impl;

import cn.cqu.dao.OrganizationDao;
import cn.cqu.pojo.Organization;
import cn.cqu.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 组织服务实现类
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationDao organizationDao;


    /**
     * 根据组织id查询
     *
     * @param orgId
     * @return
     */
    @Override
    public Organization getOrganizationByid(String orgId) {
        return organizationDao.getOrganizationById(orgId);
    }

    /**
     * 根据父级组织id查询
     *
     * @param parentId
     * @return
     */
    @Override
    public List<Organization> listOrganizationByParentId(String parentId) {
        Organization example = new Organization();
        example.setOrgParentId(parentId);
        return organizationDao.listOrganizationByExample(example);
    }

    /**
     * 根据条件动态查询
     *
     * @param example
     * @return
     */
    @Override
    public List<Organization> listOrganizationByExample(Organization example) {
        return organizationDao.listOrganizationByExample(example);
    }

    /**
     * 查询物理位置
     * @param fristid
     * @param secondId
     * @return
     */
    @Override
    public List<Organization> listMacaddressByRootID(String fristid,String secondId) {
        //定义结果集
        List<Organization> list = new ArrayList<>();
        if(!secondId.equals("all")){ //如果二级部门id不是all,就用二级部门id查询
            list = listOrganizationByParentId(secondId);
        }else {//如果二级部门id是all,就用一级部门id查询
            if(!fristid.equals("all")){
                //查询该一级部门下的二级部门合集
                List<Organization> listParent = listOrganizationByParentId(fristid);
                //遍历二级部门合集取id
                for (Organization o: listParent
                ) {
                    //取到各个二级部门的id按作parentid查物理位置
                    list.addAll(listOrganizationByParentId(o.getOrgId()));
                }
            }else {
                Organization example = new Organization();
                example.setOrgLevel(3);
                list = listOrganizationByExample(example);
            }

        }
        return list;
    }


    /**
     * 根据id删除组织
     *
     * @param id
     * @return
     */
    @Override
    public int deleteOrganizationById(String id) {
        return organizationDao.deleteOrganizationById(id);
    }

    /**
     * 修改组织名称
     *
     * @param organization
     * @return
     */
    @Override
    public int updateOrganizationName(Organization organization) {
        String orgName = organization.getOrgName();
        Organization example = new Organization();
        example.setOrgParentId(organization.getOrgParentId());
            List<Organization> list =  organizationDao.listOrganizationByExample(example);
            for (Organization org:list) {   //判断同父级部门内是否存在重名
                if (orgName.equals(org.getOrgName())){
                    return -1;
                }
            }
            return organizationDao.updateOrganizationName(orgName,organization.getOrgId());
    }

    /**
     * 增加组织
     * @param organization
     * @return
     */
    @Override
    public int insertOrganization(Organization organization) {
            Organization example = new Organization();//判断同父级部门内是否存在重名 ,第二种实现
            example.setOrgParentId(organization.getOrgParentId());
            example.setOrgName(organization.getOrgName());
            List<Organization> cheakList = organizationDao.listOrganizationByExample(example);
            if(cheakList.size()>0)
                return -1;
            else
                return organizationDao.insertOrganization(organization);

    }
}
