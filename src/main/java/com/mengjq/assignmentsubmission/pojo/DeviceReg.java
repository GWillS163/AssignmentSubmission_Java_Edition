package com.mengjq.assignmentsubmission.pojo;

import com.mengjq.assignmentsubmission.util.Device;

import java.util.Map;

public class DeviceReg {
    String deviceUser;
    String deviceName;
    String deviceMAC;
    String stuId;
    String updateTime;

    public String getUpdateTime() {
        return updateTime;
    }

    public DeviceReg() {
        setAllDeviceInfo();
        // get system time with format "yyyy-MM-dd HH:mm:ss"
        updateTime = Device.getCurrentTime();

    }

    public void setAllDeviceInfo() {
        Map<String, String> map = System.getenv();
        Device device =new Device();
        this.deviceUser = map.get("USERNAME");// 获取用户名
        this.deviceName = map.get("COMPUTERNAME");// 获取计算机名
        this.deviceMAC = device.getMAC();// 获取计算机域名
    }

    @Override
    public String toString() {
        return "DeviceReg{" +
                "deviceUser='" + deviceUser + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceMAC='" + deviceMAC + '\'' +
                ", userId='" + stuId + '\'' +
                '}';
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDeviceUser() {
        return deviceUser;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceMAC() {
        return deviceMAC;
    }
}
