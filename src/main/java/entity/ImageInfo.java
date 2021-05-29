package entity;

import java.io.Serializable;

public class ImageInfo  implements Serializable {

    private static final long serialVersionUID =  1L;

    private Integer fileId; //图片编号
    private Integer patientId; //患者编号
    private String fileName;  //图片名称
    private String fileLocalUrl; //图片存储位置
    private String category; //图片类别

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLocalUrl() {
        return fileLocalUrl;
    }

    public void setFileLocalUrl(String fileLocalUrl) {
        this.fileLocalUrl = fileLocalUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
