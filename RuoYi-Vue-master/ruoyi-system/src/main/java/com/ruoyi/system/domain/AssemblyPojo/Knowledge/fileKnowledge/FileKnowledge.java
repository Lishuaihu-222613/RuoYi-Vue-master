package com.ruoyi.system.domain.AssemblyPojo.Knowledge.fileKnowledge;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Node("FileKnowledge")
public class FileKnowledge {

    @Id
    @GeneratedValue
    private Long fileId;

    @Property(name = "label")
    private String fileName;

    @Property(name = "文件类型")
    private String fileType;

    @Property(name = "文件大小")
    private String fileSize;

    @Property(name = "文件描述")
    private String fileDescription;

    @DynamicLabels
    private Set<String> fileClassification;

    @Property(name = "文件地址")
    private String fileUrl;

    @Property(name = "经理人")
    private String manager;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Property(name = "修改时间")
    private Date modifyTime;

    public FileKnowledge() {
    }

    public FileKnowledge(Long fileId, String fileName, String fileType, String fileSize, String fileDescription, Set<String> fileClassification, String fileUrl, String manager, Date modifyTime) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fileDescription = fileDescription;
        this.fileClassification = fileClassification;
        this.fileUrl = fileUrl;
        this.manager = manager;
        this.modifyTime = modifyTime;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long id) {
        this.fileId = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public Set<String> getFileClassification() {
        return fileClassification;
    }

    public void setFileClassification(Set<String> fileClassification) {
        this.fileClassification = fileClassification;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileKnowledge that = (FileKnowledge) o;
        return Objects.equals(fileId, that.fileId) && Objects.equals(fileName, that.fileName) && Objects.equals(fileType, that.fileType) && Objects.equals(fileSize, that.fileSize) && Objects.equals(fileDescription, that.fileDescription) && Objects.equals(fileClassification, that.fileClassification) && Objects.equals(fileUrl, that.fileUrl) && Objects.equals(manager, that.manager) && Objects.equals(modifyTime, that.modifyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId, fileName, fileType, fileSize, fileDescription, fileClassification, fileUrl, manager, modifyTime);
    }

    @Override
    public String toString() {
        return "fileKnowledge{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                ", fileClassification=" + fileClassification +
                ", fileUrl='" + fileUrl + '\'' +
                ", manager='" + manager + '\'' +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
