package com.mengjq.assignmentsubmission.pojo;

import com.mengjq.assignmentsubmission.util.Device;

import java.util.Map;
import java.util.Random;

public class DeviceInfo {
    String deviceId;
    String deviceUser;
    String deviceName;
    String deviceMAC;
    String stuId;
    String registerTime;

    public String getLoginHistory() {
        return loginHistory;
    }

    String loginHistory;

    public String getRegisterTime() {
        return registerTime;
    }

    public DeviceInfo() {
        setAllDeviceInfo();
        // get system time with format "yyyy-MM-dd HH:mm:ss"
        registerTime = Device.getCurrentTime();
        deviceId = RandomString();

    }

    private String RandomString() {
        Random random = new Random();
        long randomLong = random.nextLong();
        if (randomLong < 0) {
            randomLong = -randomLong;
        }
        randomLong = randomLong % ((long) 9999999 - 1000000) + 1000000;
        return String.valueOf(randomLong);
    }

    public void setAllDeviceInfo() {
        Map<String, String> map = System.getenv();
        Device device =new Device();
        this.deviceUser = map.get("USERNAME");// 获取用户名
        this.deviceName = map.get("COMPUTERNAME");// 获取计算机名
        this.deviceMAC = Device.getMAC();// 获取计算机域名
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

    public String getDeviceUser() {
        return deviceUser;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceMAC() {
        return deviceMAC;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
