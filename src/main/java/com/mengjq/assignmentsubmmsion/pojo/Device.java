package com.mengjq.assignmentsubmmsion.pojo;

import java.util.Map;

public class Device {
    String deviceUser;
    String deviceName;
    String OS;

    public void getAll(String[] args) {
        Map<String, String> map = System.getenv();
        this.deviceUser = map.get("USERNAME");// 获取用户名
        this.deviceName = map.get("COMPUTERNAME");// 获取计算机名
        this.OS = map.get("OS");// 获取计算机域名
    }

    public String getDeviceUser() {
        return deviceUser;
    }

    public void setDeviceUser(String deviceUser) {
        this.deviceUser = deviceUser;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }
}
