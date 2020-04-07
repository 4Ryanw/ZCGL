package cn.cqu.pojo;

import java.io.Serializable;

/**
 * 设备类型实体类
 */
public class DeviceType implements Serializable {
    private String typeId;
    private String type;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
