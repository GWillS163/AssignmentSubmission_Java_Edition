package com.mengjq.assignmentsubmission.pojo;


import org.bson.Document;

public class AssignmentInfo {
    public Integer assiId;
    public String curriculum;
    public String nickName;
    public String deadLine;
    public String description;
    public String fileRule;
    public String status;

    public Integer getAssiId() {
        return assiId;
    }

    public void setAssiId(Integer assiId) {
        this.assiId = assiId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Document toDocument() {
        return new Document()
                .append("assiId", assiId)
                .append("curriculum", curriculum)
                .append("nickName", nickName)
                .append("deadLine", deadLine)
                .append("description", description)
                .append("fileRule", fileRule)
                .append("status", status);
    }
}
