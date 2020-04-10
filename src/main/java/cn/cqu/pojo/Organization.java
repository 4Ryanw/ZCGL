package cn.cqu.pojo;

import java.io.Serializable;

/**
 * 组织实体类
 */
public class Organization implements Serializable {
    private String orgId ; //组织id
    private int orgLevel; //组织等级
    private String orgParentId;//父级组织id
    private String orgName;//组织名称

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public int getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(int orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getOrgParentId() {
        return orgParentId;
    }

    public void setOrgParentId(String orgParentId) {
        this.orgParentId = orgParentId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
