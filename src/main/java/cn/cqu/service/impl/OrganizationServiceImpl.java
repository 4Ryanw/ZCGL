package cn.cqu.service.impl;

import cn.cqu.dao.OrganizationDao;
import cn.cqu.pojo.Organization;
import cn.cqu.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 组织服务实现类
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationDao organizationDao;

    /**
     * 按组织级别查询组织集合
     *
     * @param level
     * @return
     */
    @Override
    public List<Organization> listOrganizationByLevel(int level) {
        return organizationDao.listOrganizationByLevel(level);
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
        if(organization.getOrgLevel()>1){  //一级部门以下单位需要判断同父级部门内是否存在重名
            List<Organization> list =  organizationDao.listOrganizationByExample(example);
            for (Organization org:list) {
                if (orgName.equals(org.getOrgName())){
                    return -1;
                }
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
        if(organization.getOrgLevel()>1){   //一级部门以下单位需要判断同父级部门内是否存在重名 ,第二种实现
            Organization example = new Organization();
            example.setOrgParentId(organization.getOrgParentId());
            example.setOrgName(organization.getOrgName());
            List<Organization> cheakList = organizationDao.listOrganizationByExample(example);
            if(cheakList.size()>0)
                return -1;
        }
            return insertOrganization(organization);
    }
}
