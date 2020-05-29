package cn.cqu.service.impl;

import cn.cqu.dao.DeviceDao;
import cn.cqu.dao.OrganizationDao;
import cn.cqu.pojo.Organization;
import cn.cqu.pojo.dto.OrganizationDTO;
import cn.cqu.service.OrganizationService;
import cn.cqu.util.MyLog;
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
    @Autowired
    private DeviceDao deviceDao;


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
     * 查询组织结构树
     * @return
     */
    @Override
    public OrganizationDTO getOrgTree() {
        Organization root = new Organization();
        root.setOrgName("根节点");
        root.setOrgId("0");
        OrganizationDTO orgTree = listChildrenNode(root,null);
        return orgTree;
    }

    /**
     * 查询子节点并封装成DTO
     * @param parentNode
     * @return
     */
    private OrganizationDTO listChildrenNode(Organization parentNode,String parentName){

        //先把Organization转化为DTO
        OrganizationDTO  organizationDTO= new OrganizationDTO(parentNode.getOrgName(),parentName);
        //如果还有子节点,查询出来
        List<Organization> childrenList = listOrganizationByParentId(parentNode.getOrgId());
        OrganizationDTO d2;
        if(childrenList.size()>0){
            for (Organization childrenNode:childrenList//再遍历递归调用自身
            ) {
                //System.out.println(organizationDTO.getName()+"循环");
                 d2 =  listChildrenNode(childrenNode,parentNode.getOrgName());
                if(d2!=null){
                    //System.out.println(d2.getName()+"添加进"+organizationDTO.getName());
                    organizationDTO.addChildrenNode(d2); //将子节点DTO2添加进父节点DTO1
                }
            }
        }
        //返回DTO1
        return organizationDTO;
    };

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
    @MyLog(actionName = "删除组织")
    public int deleteOrganizationById(String id) {
        //删除前先判断是否存在子节点
        List<Organization> subList = listOrganizationByParentId(id);
        if(subList.size()>0){
            return -1;
        }
        //查询其下是否存在设备
        else if(deviceDao.listDeviceByOrgId(id).size()>0){
            return -2;
        }

        else{
            return organizationDao.deleteOrganizationById(id);
        }
    }

    /**
     * 修改组织名称
     *
     * @param organization
     * @return
     */
    @Override
    @MyLog(actionName = "修改组织名称")
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
    @MyLog(actionName = "增加组织")
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
