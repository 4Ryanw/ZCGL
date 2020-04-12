package cn.cqu.pojo.dto;

import cn.cqu.pojo.*;

import java.io.Serializable;
import java.util.List;

/**
 * 设备数据传输对象
 */
public class DeviceDTO implements Serializable {
    private Device device;
    private DeviceUseage deviceUseage;
    private DeviceRunning deviceRunning;
    private DeviceMaintain deviceMaintain;
    private String deviceTpe;
    private String deviceBrand;
    private String dep_fri;
    private String dep_sec;
    private String dep_mac;
    private List<Account> userList;

    public String getDeviceTpe() {
        return deviceTpe;
    }

    public void setDeviceTpe(String deviceTpe) {
        this.deviceTpe = deviceTpe;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDep_fri() {
        return dep_fri;
    }

    public void setDep_fri(String dep_fri) {
        this.dep_fri = dep_fri;
    }

    public String getDep_sec() {
        return dep_sec;
    }

    public void setDep_sec(String dep_sec) {
        this.dep_sec = dep_sec;
    }

    public String getDep_mac() {
        return dep_mac;
    }

    public void setDep_mac(String dep_mac) {
        this.dep_mac = dep_mac;
    }

    public List<Account> getUserList() {
        return userList;
    }

    public void setUserList(List<Account> userList) {
        this.userList = userList;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public DeviceUseage getDeviceUseage() {
        return deviceUseage;
    }

    public void setDeviceUseage(DeviceUseage deviceUseage) {
        this.deviceUseage = deviceUseage;
    }

    public DeviceRunning getDeviceRunning() {
        return deviceRunning;
    }

    public void setDeviceRunning(DeviceRunning deviceRunning) {
        this.deviceRunning = deviceRunning;
    }

    public DeviceMaintain getDeviceMaintain() {
        return deviceMaintain;
    }

    public void setDeviceMaintain(DeviceMaintain deviceMaintain) {
        this.deviceMaintain = deviceMaintain;
    }
}
