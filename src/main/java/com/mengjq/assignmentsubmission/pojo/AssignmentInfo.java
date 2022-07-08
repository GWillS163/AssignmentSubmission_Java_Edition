package com.mengjq.assignmentsubmission.pojo;

public class AssignmentInfo {
    Integer id;
    String curriculum;
    String nickName;
    String deadLine;
    String description;
    String fileRule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileRule() {
        return fileRule;
    }

    public void setFileRule(String fileRule) {
        this.fileRule = fileRule;
    }
}
