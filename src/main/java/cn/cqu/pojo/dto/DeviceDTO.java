package cn.cqu.pojo.dto;

import cn.cqu.pojo.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 设备数据传输对象
 */
public class DeviceDTO implements Serializable {
    //基础信息
    private String devId;
    private String typeId;
    private String brandId;
    private String type;
    private String brand;
    private String devModel;
    private Date purchaseTime;
    private String erpCode;
    private Date lastUpate;
    //使用信息
    private String depFri;
    private String depSec;
    private String depThir;
    private String address;
    private int devStatus;
    private int domainStatus;
    private int network;

    //运行信息
    private String macAddress;
    private String ipAddress;
    private String systemVersion;
    private String hdSize;
    private String cpuInfo;
    private String memoryInfo;

    //所属用户
    private List<Account> userList;

    public String getDevId() {
        return devId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDevId(String devId) {
        this.devId = devId;
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

    public Date getLastUpate() {
        return lastUpate;
    }

    public void setLastUpate(Date lastUpate) {
        this.lastUpate = lastUpate;
    }

    public String getDepFri() {
        return depFri;
    }

    public void setDepFri(String depFri) {
        this.depFri = depFri;
    }

    public String getDepSec() {
        return depSec;
    }

    public void setDepSec(String depSec) {
        this.depSec = depSec;
    }

    public String getDepThir() {
        return depThir;
    }

    public void setDepThir(String depThir) {
        this.depThir = depThir;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(int devStatus) {
        this.devStatus = devStatus;
    }

    public int getDomainStatus() {
        return domainStatus;
    }

    public void setDomainStatus(int domainStatus) {
        this.domainStatus = domainStatus;
    }

    public int getNetwork() {
        return network;
    }

    public void setNetwork(int network) {
        this.network = network;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getHdSize() {
        return hdSize;
    }

    public void setHdSize(String hdSize) {
        this.hdSize = hdSize;
    }

    public String getCpuInfo() {
        return cpuInfo;
    }

    public void setCpuInfo(String cpuInfo) {
        this.cpuInfo = cpuInfo;
    }

    public String getMemoryInfo() {
        return memoryInfo;
    }

    public void setMemoryInfo(String memoryInfo) {
        this.memoryInfo = memoryInfo;
    }

    public List<Account> getUserList() {
        return userList;
    }

    public void setUserList(List<Account> userList) {
        this.userList = userList;
    }
}
