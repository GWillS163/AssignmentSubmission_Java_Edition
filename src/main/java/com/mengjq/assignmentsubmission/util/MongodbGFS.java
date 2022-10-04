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
import com.mongodb.MongoGridFSException;
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
    private GridFSBucket gridFSBucket;

    // 初始化
    public MongodbGFS(MongoDatabase clazzDB, String filesDB) {
        // 自定义bucket name
        gridFSBucket = GridFSBuckets.create(clazzDB, filesDB);
    }

    public boolean uploadByGFS(String url) {
        InputStream ins = null;
        ObjectId fileid = null;
        // 配置一些参数
        GridFSUploadOptions options = null;
        // 截取文件名
        String filename = url.substring((url.lastIndexOf("\\") + 1), url.length());
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
        return true;
    }


    // 将文件存储到mongodb,返回存储完成后的ObjectID
    public ObjectId saveFile(String url) {
        InputStream ins = null;
        GridFSUploadOptions options = null;
        ObjectId fileid = null;
        // 配置一些参数

        // 截取文件名
        String filename = url.substring((url.lastIndexOf("\\") + 1), url.length());
        try {
            ins = new FileInputStream(url);
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
//        List<String> list = new ArrayList<String>();
//        gridFSBucket.find().forEach(new Block<GridFSFile>() {
//            @Override
//            public void apply(GridFSFile t) {
//                list.add(t.getFilename());
//            }
//        });
//        return list;
//    }

    // 删除文件
    public void deleteFile(ObjectId id) {
        try{
            gridFSBucket.delete(id);
        } catch (MongoGridFSException ignored){
            System.out.println( "File not found: " + id.toString());
        }
    }

    // 重命名文件
    public void rename(ObjectId id, String name) {
        gridFSBucket.rename(id, name);
    }

    // 将数据库中的文件读出到磁盘上，参数，文件路径
    public String downOneFile(String localPath, ObjectId id) {
        FileOutputStream out = null;
        String result=null;
        // combine localPath and filename
        try {
            out = new FileOutputStream(localPath);

            gridFSBucket.downloadToStream(id, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                assert out != null;
                out.close();
                result=out.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}