[![OSCS Status](https://www.oscs1024.com/platform/badge/.svg?size=small)](https://www.murphysec.com/dr/yOeb26pRLRus7V4HaN)
[![Build Status](https://app.travis-ci.com/GWillS163/AssignmentSubmission_Java_Edition.svg?branch=master)](https://app.travis-ci.com/GWillS163/AssignmentSubmission_Java_Edition)
[![Coveralls](https://img.shields.io/coveralls/xcatliu/pagic.svg)](https://coveralls.io/github/xcatliu/pagic)

# Content
[简介](#简介)
[Introduce](#Introduce)


# 简介
设计的桌面端用来方便的收集/提交文件

# 解决痛点
用来师生间收集指定作业（文件）

场景1：多个老师指定不同的作业格式及具有规则的文件名，学生需要提交自己的多个作业以相应的文件名重命名给多个老师。
例如：

A老师要求学生提交的文件名为：学号_姓名_作业1.docx 学号_姓名_作业2.docx

B老师要求学生提交的文件名为：学号_作业B.docx

# 使用方式
1. 首次下载或需要更改信息时，学生双击打开桌面垃圾桶，按提示输入学号，回车保存退出程序。
2. 每次使用时，将(多个)文件拖拽至本垃圾桶，将会打开软件。
3. 按屏幕提示输入每个文件指定的序号。
4. 回车即可发送文件。

# 主要功能
1. 收集并重命名文件
2. 作业数据持久化
3. 可查询/修改/删除 云端作业数据

## 安全功能
由于方便了学生配置，仅需要输入学号认证，得到个人信息。因此可能存在其他学生代替上交的可能性，因此增加了两张数据表。
1. 设备信息表： 存储设备信息及所属当前用户
2. 操作记录表： 操作记录（设备，学号，操作时间，操作IP, 操作类型[登录/更新设备/提交文件], 相关文件）

# 技术栈 & 赞助资源
- 在线配置： ConfigCat by GitHub
- 代码辅助: Copilot 插件 by GitHub
- 云数据库: MongoDB Altas by MongoDB Official.

以上这些是非常棒的技术资源并且几乎是免费的，感谢这些技术的提供方.

# 系列项目介绍
Java Desktop Edition 之前，还有Python Desktop Edition的版本。
Python Desktop Edition:

Java Desktop Edition的主要作用是解决了Python 版的一些问题如仅能单向提交无法查询删除等，并且提供了更好的体验。

每隔一段时间我再回看自己的代码，发现自己的代码质量还是有很大的提升空间，所以总是在不断的重构代码，以提高代码质量。


# 个性化安装
1. 下载源码: git clone ...
2. 安装依赖: mvn install
3. 查看本项目wiki, 了解如何配置本项目。
4. 打包运行项目

# Introduce
# About AssignmentSubmission_Java_Edition
## The reconstruction of the assignment submission tool.

before this, there are one tool developed by python using related libs.
but there were some things I encountered that were a hassle. one of the things is GUI when using GUI to develop, the consequential file size will be bigger than 50MB, but there in fact only 9MB before using GUI.


# 说明 (Chinese)
pojo: 实体类
mapper: 用于将数据库中的数据映射到实体类中
service: 用于处理业务逻辑

deviceId: 设备ID

Haha, Let's see what's new seriously!

1. no invasion configured yet, such as no edit user File, no config.txt in user's folder.

## Features of The Newest Version

> that is to use mature technology to resolve current questions I need to be solved.

- Java edition should only get users' inputs and mechanical processes, with no data should be stored in the program.
- all of the data should be stored in the database ( now using MongoDB Atlas), For example, users' data, assignments data, files data, and the new feature is that save the user's computer data so that related account.
- new construction and design of the tool, I will use the philosophy of software engineering to design my tool.
- the large file can be stored in the cloud DB.
- the retrieve message that the user queries all of the statuses of the files can be accomplished.

## 数据库表设计 DBName Collection Comment

<div align=center >
<img src="https://user-images.githubusercontent.com/49674629/179160937-f0f3a9e3-4414-43e2-8349-5574c9d44715.png" width="400" align="center"/>
</div>

## Now design

<div align=center >
<img src="readme_md_files/activityDiagram_1.jpg?v=1&type=image" width="600" align="center"/>
</div>

## About File upload Module

the maximum size of MongoDB allowed is 16MB, so we can use Grid FS like a bucket to save the large file.

<div align=center>
<img src="readme_md_files/6219abd0-0267-11ed-8a18-1b2e03e7ecbb.jpeg?v=1&type=image" width="300"/>
</div>

## the description of an older version

maybe this project should be public on GitHub as a repository?

> developed and continuous update by python within 4 months range.
> https://github.com/GWillS163/AssignmentSubmission_Python_Edition

<div align=center>
<img src="readme_md_files/b1b79940-0267-11ed-8a18-1b2e03e7ecbb.jpeg?v=1&type=image" width="120"/>
</div>
updated at 2022-7-13 13:02:09

Post at 2022-7-5 14:53:12
