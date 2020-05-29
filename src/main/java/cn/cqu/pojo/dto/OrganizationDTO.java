package cn.cqu.pojo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 组织结构树DTO
 */
public class OrganizationDTO implements Serializable {
    //节点名称
    private String name;
    //父节点名称
    private String parent;
    //子节点集合
    private List<OrganizationDTO> children = new ArrayList<>();

    public OrganizationDTO() {
    }

    public OrganizationDTO(String name, String parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<OrganizationDTO> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationDTO> children) {
        this.children = children;
    }

    public void addChildrenNode(OrganizationDTO childrenNode){
        this.children.add(childrenNode);
    }
}
