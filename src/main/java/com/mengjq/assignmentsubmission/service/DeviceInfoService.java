package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.mapper.DeviceInfoMapper;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class DeviceInfoService {
    public DeviceInfoMapper deviceInfoMapper;

    public DeviceInfoService(MongoDatabase clazzDB, String deviceReg) {
        deviceInfoMapper = new DeviceInfoMapper(clazzDB, deviceReg);
    }

    // 上传注册设备信息
    public void regCurrentDevice(DeviceInfo deviceInfo) {
        // find the device exist or not
        Document existDevice = deviceInfoMapper.request(new Document("deviceMAC", deviceInfo.getDeviceMAC())).first();
        // 如果当前设备不存在于数据库，则注册
        if (existDevice == null) {
            deviceInfoMapper.create(deviceInfo);
            System.out.println("DeviceReg success!");
        } else {
            // 如果是已经注册过的设备，则更新(时间)
            existDevice.put("loginHistory", java.time.LocalDateTime.now() + "\n" + existDevice.get("loginHistory"));
            deviceInfoMapper.update(existDevice.get("deviceMAC").toString(), existDevice);
            System.out.println("DeviceReg update success! The device has been registered!");
        }
    }

    // 通过字段 获得用户的设备信息
    public String findUserIdByDeviceMAC(String deviceMAC) {
        FindIterable<Document> res = deviceInfoMapper.request(new Document("deviceMAC", deviceMAC));
        if (res == null || res.first() == null) {
            return null;
        } else {
            return res.first().get("userId").toString();
        }
    }

}
