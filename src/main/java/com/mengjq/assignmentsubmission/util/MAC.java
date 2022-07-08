// this is an util to work for DeviceReg by get the MAC address of the computer
package com.mengjq.assignmentsubmission.util;

import java.net.NetworkInterface;
import java.util.Enumeration;

//generate a unique MAC address for windows
public class MAC {
    public static String getMAC() {
        String mac = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < hardwareAddress.length; i++) {
                        stringBuilder.append(String.format("%02X%s", hardwareAddress[i], (i < hardwareAddress.length - 1) ? "-" : ""));
                    }
                    mac = stringBuilder.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }
}