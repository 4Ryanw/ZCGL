package cn.cqu.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备基础信息实体类
 */
public class Device implements Serializable {
    private String devId;
    private String typeId;
    private String brandId;
    private String devModel;
    private Date purchaseTime;
    private String erpCode;
    private Date lastUpate;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Date getLastUpate() {
        return lastUpate;
    }

    public void setLastUpate(Date lastUpate) {
        this.lastUpate = lastUpate;
    }


    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDevModel() {
        return devModel;
    }

    public void setDevModel(String devModel) {
        this.devModel = devModel;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }
}
