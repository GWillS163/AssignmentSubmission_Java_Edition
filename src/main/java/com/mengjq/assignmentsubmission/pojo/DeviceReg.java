package com.mengjq.assignmentsubmission.pojo;

import com.mengjq.assignmentsubmission.util.MAC;

import java.util.Map;

public class DeviceReg {
    String deviceUser;
    String deviceName;
    String deviceMAC;
    String userId;

    public DeviceReg(String userId) {
        setAllWithUserId(userId);
    }

    public void setAllWithUserId(String userId) {
        Map<String, String> map = System.getenv();
        MAC mac =new MAC();
        this.deviceUser = map.get("USERNAME");// 获取用户名
        this.deviceName = map.get("COMPUTERNAME");// 获取计算机名
        this.deviceMAC = mac.getMAC();// 获取计算机域名
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DeviceReg{" +
                "deviceUser='" + deviceUser + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceMAC='" + deviceMAC + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
