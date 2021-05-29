package dto;

import java.io.Serializable;

public class PatientQueryDto implements Serializable {

    private static final long serialVersionUID =  1L;

    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 患者编号
     */
    private Integer patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 提交时间
     */
    private String submitDate;

    /**
     * 所患疾病
     */
    private String disease;

    /**
     * 处理状态
     */
    private Integer processState;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Integer getProcessState() {
        return processState;
    }

    public void setProcessState(Integer processState) {
        this.processState = processState;
    }
}
