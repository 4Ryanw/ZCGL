package cn.cqu.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备维护信息实体类
 */
public class DeviceMaintain implements Serializable {
    private String devId;
    private String maintainInfo;
    private Date maintainDate;

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getMaintainInfo() {
        return maintainInfo;
    }

    public void setMaintainInfo(String maintainInfo) {
        this.maintainInfo = maintainInfo;
    }

    public Date getMaintainDate() {
        return maintainDate;
    }

    public void setMaintainDate(Date maintainDate) {
        this.maintainDate = maintainDate;
    }
}
