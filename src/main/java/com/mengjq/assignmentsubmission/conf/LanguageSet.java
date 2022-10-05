package com.mengjq.assignmentsubmission.conf;

public class LanguageSet {
    public String currentStudentInfo;
    public String sendModePrompt;
    public String setStuInfoFirst;
    public String sendUploadOver;
    public String menuRenameSelectPrompt;
    public String menuPlzInputAssiId;
    public String menuAssiIdNotExist;
    public String selectLoopSelected;
    public String selectLoopSelectInvalid;
    public String menu1title;
    public String menu2title;
    public String menu3title;
    public String menu4title;
    public String menu5title;
    public String setModeInvalidInput;
    public String echoCLIShowAllStatusNoAssign;
    public String echoCLIShowAllStatusNoFile;
    public String echoCLIShowAllStatusNoStu;
    public String echoCLIShowAllStatusUnknown;
    public String menuAbout;
    public String sendModeAnyKeyContinue;
    public String sendUploadFailed;
    public String echoCLIMySubmitsInvalidInput;
    public String deleteSuccess;
    public String downloadSuccess;
    public String exitManage;
    public String timeWait;

    public LanguageSet(String nationCode){
    // I want to build a more Generic Tool, so I can help more people. :D
        if (nationCode == null){
            nationCode = "en";
        }
        switch (nationCode){
            case "cn":
            case "CN":
                setWordInCN();
                break;
            case "en":
            case "EN":
            default:
                setWordInEN();
                break;
        }
    }

    // set all word in Chinese here
    public void setWordInCN() {
        currentStudentInfo = "当前学生信息";
        sendModePrompt = "\t准备提交: ";
        setStuInfoFirst = "请先设置学生信息";
        sendUploadOver= "上交处理结束！";
        sendUploadFailed = "上交失败！";
        menuRenameSelectPrompt = "这个作业对应于:";
        menuPlzInputAssiId = "\t请输入此作业对应的作业编号:";
        menuAssiIdNotExist = "\t作业编号不存在，请重新输入！";
        selectLoopSelected = "已选择:";
        selectLoopSelectInvalid = "选择无效，请重新选择！\r";
        menu1title = "1. 注册学生信息";
        menu2title = "2. 管理已提交作业(云端)";
        menu3title = "3. 查看所有人提交(云端)";
        menu4title = "4. 关于";
        menu5title = "5. 退出";
        setModeInvalidInput = "无效输入!";
        echoCLIShowAllStatusNoAssign = "没有作业！";
        echoCLIShowAllStatusNoFile = "没有提交文件！";
        echoCLIShowAllStatusNoStu = "未找到学生信息";
        echoCLIShowAllStatusUnknown = "未知";
        sendModeAnyKeyContinue = "按任意键继续";
        echoCLIMySubmitsInvalidInput = "无效输入！\n" +
                "删除作业: del [id(,id,id,...)]  \n" +
                "\t例如: del 1 & del 2,5,2\n" +
                "下载作业: down [id(,id,id,...)]  \n" +
                "\t例如: down 1, down 2,5,2\n" +
                "退出: exit \n";
        deleteSuccess = "删除成功！";
        downloadSuccess = "下载成功！";
        exitManage = "[Enter]退出管理";
        timeWait = "即将退出 ";
        menuAbout = "\n" +
                "\n" +
                "     _            _      ____        _     \n" +
                "    / \\   ___ ___(_)    / ___| _   _| |__  \n" +
                "   / _ \\ / __/ __| |____\\___ \\| | | | '_ \\ \n" +
                "  / ___ \\\\__ \\__ \\ |_____|__) | |_| | |_) |\n" +
                " /_/   \\_\\___/___/_|    |____/ \\__,_|_.__/ \n" +
                "" +
                "Release Version: 1.0.0\n" +
                "Author: Mengjq\n" +
                "Github:\n" +
                "Java Desktop Edition: git@github.com:GWillS163/AssignmentSubmission_Java_Edition.git\n" +
                "Java Web Edition: ***\n" +
                "Python Desktop Edition: ***\n" +
                "Python Web Edition: ***";

    }

    // set all word in English here
    public void setWordInEN() {
        currentStudentInfo = "Current Student Info";
        sendModePrompt = "Ready to Submit";
        setStuInfoFirst = "Please set student info first";
        menuRenameSelectPrompt = "Which assignments fit with :";
        sendUploadOver = "upload finished!";
        sendUploadFailed = "upload failed!";
        menuPlzInputAssiId = "\tPlease input the assiId you want to rename:";
        menuAssiIdNotExist = "\tThe assiId is not exist! input again!";
        selectLoopSelected = "Selected:";
        selectLoopSelectInvalid = "Invalid input! input again!\r";
        menu1title = "1. Configure personal information";
        menu2title = "2. View submitted assignments (cloud)";
        menu3title = "3. View all submitted (cloud)";
        menu4title = "4. About";
        menu5title = "5. Exit";
        setModeInvalidInput = "Invalid input!";
        echoCLIShowAllStatusNoAssign = "No assignments!";
        echoCLIShowAllStatusNoFile = "No files!";
        echoCLIShowAllStatusNoStu = "No student info found";
        echoCLIShowAllStatusUnknown = "Unknown";
        sendModeAnyKeyContinue = "Press any key to continue";
        echoCLIMySubmitsInvalidInput = "无效输入！\n" +
                "删除作业: del [id(,id,id,...)]  \n" +
                "\t例如: del 1 & del 2,5,2\n" +
                "下载作业: down [id(,id,id,...)]  \n" +
                "\t例如: down 1, down 2,5,2\n" +
                "退出: exit \n";
        deleteSuccess = "删除成功！";
        downloadSuccess = "下载成功！";
        exitManage = "[Enter]退出管理";
        timeWait = "即将退出 ";
        menuAbout = "/***\n" +
                " *                                                                                                                                                                                                                         \n" +
                " *          _/_/                        _/                                                            _/            _/_/_/            _/                                        _/                                         \n" +
                " *       _/    _/    _/_/_/    _/_/_/        _/_/_/  _/_/_/    _/_/_/  _/_/      _/_/    _/_/_/    _/_/_/_/      _/        _/    _/  _/_/_/    _/_/_/  _/_/    _/_/_/  _/_/          _/_/_/    _/_/_/    _/_/    _/_/_/    \n" +
                " *      _/_/_/_/  _/_/      _/_/      _/  _/    _/  _/    _/  _/    _/    _/  _/_/_/_/  _/    _/    _/            _/_/    _/    _/  _/    _/  _/    _/    _/  _/    _/    _/  _/  _/_/      _/_/      _/    _/  _/    _/   \n" +
                " *     _/    _/      _/_/      _/_/  _/  _/    _/  _/    _/  _/    _/    _/  _/        _/    _/    _/                _/  _/    _/  _/    _/  _/    _/    _/  _/    _/    _/  _/      _/_/      _/_/  _/    _/  _/    _/    \n" +
                " *    _/    _/  _/_/_/    _/_/_/    _/    _/_/_/  _/    _/  _/    _/    _/    _/_/_/  _/    _/      _/_/      _/_/_/      _/_/_/  _/_/_/    _/    _/    _/  _/    _/    _/  _/  _/_/_/    _/_/_/      _/_/    _/    _/     \n" +
                " *                                           _/                                                                                                                                                                            \n" +
                " *                                      _/_/                                                                                                                                                                               \n" +
                " */" +
                "Release Version: 1.0.0\n" +
                "Author: Mengjq\n" +
                "Github:\n" +
                "Java Desktop Edition: git@github.com:GWillS163/AssignmentSubmission_Java_Edition.git\n" +
                "Java Web Edition: ***\n" +
                "Python Desktop Edition: ***\n" +
                "Python Web Edition: ***";

    }
}
