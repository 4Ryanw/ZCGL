package cn.cqu.pojo;

import java.io.Serializable;

/**
 * 设备使用信息实体类
 */
public class DeviceUseage implements Serializable {
    private String devId;
    private String depFri;
    private String depSec;
    private String depThir;
    private String address;
    private int devStatus;
    private int domainStatus;
    private int network;

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
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

}
