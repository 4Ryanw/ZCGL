package cn.cqu.dao;

import cn.cqu.pojo.Organization;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 组织Dao类
 */
@Repository
public interface OrganizationDao {

 /**
  * 按id查找组织
  * @return
  */
 @Select("select * from t_organization where org_id = #{id}")
  Organization getOrganizationById(String id);


 /**
  * 根据传入条件动态查询组织
  * @return
  */
 List<Organization> listOrganizationByExample(Organization example);

 /**
  * 插入组织数据
  * @param organization
  * @return
  */
 @Insert("insert into t_organization values(UUID(),#{orgName},#{orgParentId},#{orgLevel})")
  int insertOrganization(Organization organization);

 /**
  * 通过id删除组织
  * @param orgId
  * @return
  */
 @Delete("delete from t_organization where org_id = #{orgId} ")
 int deleteOrganizationById(String orgId);

 /**
  * 通过id修改组织名称
  * @param orgName
  * @param orgId
  * @return
  */
 @Update("update t_organization set org_name = #{orgName} where org_id = #{orgId}")
 int updateOrganizationName(@Param("orgName") String orgName, @Param("orgId") String orgId);



}
