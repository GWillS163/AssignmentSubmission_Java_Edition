package com.mengjq.assignmentsubmmsion.util;

import com.opslab.util.PropertiesUtil;
import org.apache.velocity.texen.util.PropertiesUtil;

import java.net.URL;


public class ConnectionProperty {
    //MongoDB服务器的IP
    private String host;
    //MongoDB连接端口号
    private int port;
    //需要进行连接的数据库名
    private String database;
    private String path = "src/main/resources/properties/db.properties";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public ConnectionProperty(){
        UpdateProperty(path);
    }

    public void UpdateProperty(String path){
        this.path = path;
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        try{
            host = propertiesUtil.GetValueByKey(path,"mongodb.host");
            port = Integer.parseInt(propertiesUtil.GetValueByKey(path,"mongodb.port"));
            database = propertiesUtil.GetValueByKey(path,"mongodb.database");
        }catch (Exception e){
            //这里添加一层文件保护，为防止读取不到配置文件导致出错
            e.printStackTrace();
        }
    }
}
