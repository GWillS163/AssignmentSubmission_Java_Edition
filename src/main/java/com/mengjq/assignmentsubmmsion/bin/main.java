package com.mengjq.assignmentsubmmsion.bin;

import com.mengjq.assignmentsubmmsion.pojo.AssignmentInfo;
import com.mengjq.assignmentsubmmsion.pojo.FileInfo;
import com.mengjq.assignmentsubmmsion.service.impl.AssignmentInfoServiceImpl;
import com.mengjq.assignmentsubmmsion.util.Menu;
import com.mengjq.assignmentsubmmsion.util.DragFiles;

import java.util.List;

public class main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Open the
        // 主程序是
        //
        //双击配置信息
        // select menu function
        Menu.printMenu();
        Menu.selectMenu();

        //选择1 输入学生信息 （保存至云端？识别当前设备特征为.xxx）
        //   1 检索相似信息并保存至本地
        //选择2 查询提交状态
        //   输出所有数据库中提交的信息
        //选择3 退出程序

        //发送文件
        //0. get drag info from user
        DragFiles dragFiles = new DragFiles();
        List<FileInfo> fileInfos = dragFiles.getDragFileInfo(args);
        //4. 获取文件上传时间

        //1. [Record] download the latest assignment data from the cloud， and print
        AssignmentInfoServiceImpl assignmentInfoServiceImpl = new AssignmentInfoServiceImpl();
        List<AssignmentInfo> assignmentInfos = assignmentInfoServiceImpl.printAssignmentInfo();

//        //2. 展示最新Assignment数据
//        printAssignmentData(assignmentData);
//        //3. [Record] 获取用户选择
//        getuserchoice();
//        //4. [Record] 尝试重命名文件，并提示报错 if exists
//        renamefiles();
//        //5. [Record] 上交 Files
//        uploadfiles();

    }
}
