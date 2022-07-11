package com.mengjq.assignmentsubmission.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;

/**
 *
 * @author zhaotong
 *
 */
public class MongodbGFS {

    private MongoClient mongoClient;

    // 我们进行操作的数据库
    private MongoDatabase clazzDB;

    // bucket
    private GridFSBucket gridFSBucket;

    // 初始化
    {
        Config conf = new Config();

        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        clazzDB = mongoClient.getDatabase(conf.clazz);
        // 自定义bucket name
        gridFSBucket = GridFSBuckets.create(clazzDB, "Files");
        // 使用默认的名字
        // gridFSBucket=GridFSBuckets.create(useDatabase);
    }

    // 将文件存储到mongodb,返回存储完成后的ObjectID
    public ObjectId saveFile(String url) {
        InputStream ins = null;
        ObjectId fileid = null;
        // 配置一些参数
        GridFSUploadOptions options = null;
        // 截取文件名
        String filename = url.substring((url.lastIndexOf("/") + 1), url.length());
        try {
            ins = new FileInputStream(new File(url));
            options = new GridFSUploadOptions().chunkSizeBytes(303200).metadata(new Document("type", "presentation"));

            // 存储文件，第一个参数是文件名称，第二个是输入流,第三个是参数设置
            fileid = gridFSBucket.uploadFromStream(filename, ins, options);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ins.close();
            } catch (IOException e) {
            }
        }
        return fileid;
    }

    // 通过OpenUploadStream存储文件
    /**
     *
     * The GridFSUploadStream buffers data until it reaches the chunkSizeBytes and
     * then inserts the chunk into the chunks collection. When the
     * GridFSUploadStream is closed, the final chunk is written and the file
     * metadata is inserted into the files collection.
     *
     */
    public ObjectId saveFile2(String url) {
        ObjectId fileid = null;
        GridFSUploadStream gfsupload = null;
        // 配置一些参数
        GridFSUploadOptions options = null;
        // 截取文件名
        String filename = url.substring((url.lastIndexOf("/") + 1), url.length());
        try {
            options = new GridFSUploadOptions().chunkSizeBytes(358400).metadata(new Document("type", "presentation"));

            // 存储文件，第一个参数是文件名称，第二个是输入流,第三个是参数设置
            gfsupload = gridFSBucket.openUploadStream(filename, options);

            byte[] data = Files.readAllBytes(new File(url).toPath());

            gfsupload.write(data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            gfsupload.close();
            fileid = gfsupload.getObjectId();
        }
        return fileid;
    }

    // 查询所有储存的文件
//    public List<String> findAllFile() {
//        List<String> filenames = new ArrayList<>();
//
//        gridFSBucket.find().forEach(new Block<GridFSFile>() {
//
//            @Override
//            public void apply(GridFSFile t) {
//                filenames.add(t.getFilename());
//            }
//        });
//
//        return filenames;
//    }

    // 删除文件
    public void deleteFile(ObjectId id) {
        gridFSBucket.delete(id);
    }

    // 重命名文件
    public void rename(ObjectId id, String name) {
        gridFSBucket.rename(id, name);
    }

    // 将数据库中的文件读出到磁盘上，参数，文件路径
    public String downFile(String url, ObjectId id) {
        FileOutputStream out = null;
        String result=null;
        try {
            out = new FileOutputStream(new File(url));
            gridFSBucket.downloadToStream(id, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
                result=out.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}