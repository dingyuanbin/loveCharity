package entity;

import java.io.Serializable;

public class Patient  implements Serializable {

    private static final long serialVersionUID =  1L;

    private Integer patientId;           //患者编号
    private String submitDate;          //提交时间
    private String name;               //患者姓名
    private String sex;               //患者性别
    private Integer age;               //患者年龄
    private String job;               //患者职业
    private String disease;           //所患疾病
    private String date;              //确诊日期
    private String hospital;          //目前所在医院
    private String doctor;            //主治医生
    private String doctorPhoneNum;    //主治医生联系电话
    private String background;        //家庭背景
    private String illnessExperience; //患病经历
    private String insurance;         //保险
    private String reimbursementRatios;//报销比例
    private String fundRaisingChannel;//筹款渠道
    private String amountReceived;    //目前已获得资助的金额
    private String fixedAssets;       //固定资产
    private Integer familySize;        //家庭人口数量
    private String costGap;           //后续治疗费用缺口
    private String homeAddress;       //家庭住址
    private Integer processingStatus;    //处理状态

    public Integer getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(Integer processingStatus) {
        this.processingStatus = processingStatus;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoctorPhoneNum() {
        return doctorPhoneNum;
    }

    public void setDoctorPhoneNum(String doctorPhoneNum) {
        this.doctorPhoneNum = doctorPhoneNum;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getIllnessExperience() {
        return illnessExperience;
    }

    public void setIllnessExperience(String illnessExperience) {
        this.illnessExperience = illnessExperience;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getReimbursementRatios() {
        return reimbursementRatios;
    }

    public void setReimbursementRatios(String reimbursementRatios) {
        this.reimbursementRatios = reimbursementRatios;
    }

    public String getFundRaisingChannel() {
        return fundRaisingChannel;
    }

    public void setFundRaisingChannel(String fundRaisingChannel) {
        this.fundRaisingChannel = fundRaisingChannel;
    }

    public String getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(String amountReceived) {
        this.amountReceived = amountReceived;
    }

    public String getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(String fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public Integer getFamilySize() {
        return familySize;
    }

    public void setFamilySize(Integer familySize) {
        this.familySize = familySize;
    }

    public String getCostGap() {
        return costGap;
    }

    public void setCostGap(String costGap) {
        this.costGap = costGap;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getHelpLetter() {
        return helpLetter;
    }

    public void setHelpLetter(String helpLetter) {
        this.helpLetter = helpLetter;
    }

    public String getContactPhoneNum() {
        return contactPhoneNum;
    }

    public void setContactPhoneNum(String contactPhoneNum) {
        this.contactPhoneNum = contactPhoneNum;
    }

    public String getContactWechat() {
        return contactWechat;
    }

    public void setContactWechat(String contactWechat) {
        this.contactWechat = contactWechat;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    private String contactName;       //联系人姓名
    private String helpLetter;        //求助信
    private String contactPhoneNum;   //联系人电话
    private String contactWechat;     //联系人微信
    private String relation;          //联系人与患者关系

}
