package com.mengjq.assignmentsubmmsion.util;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Connection {


    //创建一个可以获取db.properties中MongoDB连接内容的对象
    private ConnectionProperty connectionProperty;
    //单例模式中将MongoDB的连接对象设置为静态变量
    private static Connection connection;
    //MongoDB的数据库连接操作对象
    private MongoClient mongoClient;
    private Connection(){
        connectionProperty = new ConnectionProperty();
    }

    /**
     * 设置MongoDB的连接字符串，如果没有则默认为db.properties中的内容
     * @param connectionProperty
     */
    public void setConnectionProperty(ConnectionProperty connectionProperty) {
        this.connectionProperty = connectionProperty;
    }

    /**
     *
     * @return 返回MongoDB数据库操作连接对象MongoDataBase,为增加对数据库的操作安全性所以没有将此对象设置为单例对象
     * @throws Exception 抛出数据库连接异常
     */
    public MongoDatabase getMongodb() throws Exception {
        //创建一个数据库连接对象
        mongoClient = new MongoClient(connectionProperty.getHost(),connectionProperty.getPort());
        //获取数据库操作对象,并连接数据库
        MongoDatabase mongodb = mongoClient.getDatabase(connectionProperty.getDatabase());
        if (mongodb.getName()!=null)
            return mongodb;
        else
            throw new Exception("数据库连接出错");
    }

    /**
     * 单例模式中唯一可以获取到MongoDB数据库操作对象的方式
     * @return 返回MongoDB数据库连接对象Connection
     */
    public static Connection getInstance() {
        if (connection != null)
            return connection;
        else{
            connection = new Connection();
        }
        return connection;
    }
}

