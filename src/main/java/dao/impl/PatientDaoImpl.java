package dao.impl;

import dao.PatientDao;
import dto.PatientQueryDto;
import entity.Patient;
import utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl extends JdbcUtil implements PatientDao {
    @Override
    public int insertGetKey(Patient obj) throws Exception {
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        String sql = "INSERT INTO tb_patient_info(patientName,disease,submitDate,sex,age,job,date,hospital,doctor,doctorPhoneNum,background,illnessExperience,insurance,reimbursementRatios,fundRaisingChannel,amountReceived,fixedAssets,familySize,costGap,homeAddress,contactName,helpLetter,contactPhoneNum,contactWechat,relation)" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {
                obj.getName(),
                obj.getDisease(),
                obj.getSubmitDate(),
                obj.getSex(),
                obj.getAge(),
                obj.getJob(),
                obj.getDate(),
                obj.getHospital(),
                obj.getDoctor(),
                obj.getDoctorPhoneNum(),
                obj.getBackground(),
                obj.getIllnessExperience(),
                obj.getInsurance(),
                obj.getReimbursementRatios(),
                obj.getFundRaisingChannel(),
                obj.getAmountReceived(),
                obj.getFixedAssets(),
                obj.getFamilySize(),
                obj.getCostGap(),
                obj.getHomeAddress(),
                obj.getContactName(),
                obj.getHelpLetter(),
                obj.getContactPhoneNum(),
                obj.getContactWechat(),
                obj.getRelation()
        };
        int primaryKey = updateGetKey(sql, params);

        // 释放资源
        close();
        return primaryKey;
    }

    @Override
    public int updatePatientInfo(Patient obj) throws Exception {
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        String sql = "UPDATE tb_patient_info" + " SET patientName=?,"
                +"disease=?,"
                +"sex=?,"
                +"age=?,"
                +"job=?,"
                +"date=?,"
                +"hospital=?,"
                +"doctor=?,"
                +"doctorPhoneNum=?,"
                +"background=?,"
                +"illnessExperience=?,"
                +"insurance=?,"
                +"reimbursementRatios=?,"
                +"fundRaisingChannel=?,"
                +"amountReceived=?,"
                +"fixedAssets=?,"
                +"familySize=?,"
                +"costGap=?,"
                +"homeAddress=?,"
                +"contactName=?,"
                +"helpLetter=?,"
                +"contactPhoneNum=?,"
                +"contactWechat=?,"
                +"relation=?"
                + " WHERE patientId=?";
        Object[] params = {obj.getName(),
                obj.getDisease(),
                obj.getSex(),
                obj.getAge(),
                obj.getJob(),
                obj.getDate(),
                obj.getHospital(),
                obj.getDoctor(),
                obj.getDoctorPhoneNum(),
                obj.getBackground(),
                obj.getIllnessExperience(),
                obj.getInsurance(),
                obj.getReimbursementRatios(),
                obj.getFundRaisingChannel(),
                obj.getAmountReceived(),
                obj.getFixedAssets(),
                obj.getFamilySize(),
                obj.getCostGap(),
                obj.getHomeAddress(),
                obj.getContactName(),
                obj.getHelpLetter(),
                obj.getContactPhoneNum(),
                obj.getContactWechat(),
                obj.getRelation(),
                obj.getPatientId()};
        int rows = update(sql, params);
        // 释放资源
        close();
        return rows;
    }

    @Override
    public int insert(Patient obj) throws Exception {
        return 0;
    }

    @Override
    public int update(Patient obj) throws Exception {
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        String sql = "UPDATE tb_patient_info" + " SET processingStatus=?" + " WHERE patientId=?";
        Object[] params = {obj.getProcessingStatus(), obj.getPatientId()};
        int rows = update(sql, params);
        // 释放资源
        close();
        return rows;
    }


    @Override
    public int delete(String id) throws Exception {
       return 0;
    }

    @Override
    public int delete(Integer id) throws Exception {
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        String sql = "delete from tb_patient_info " + " WHERE patientId=" + id + "";
        Object[] params = {};
        int rows = update(sql, params);
        // 释放资源
        close();
        return rows;
    }

    @Override
    public Patient selectById(String id) throws Exception {
        return  null;
    }

    @Override
    public Patient selectById(Integer id) throws Exception {
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        String sql = "SELECT * FROM tb_patient_info WHERE patientId=?";
        Object params[] = {id};
        ResultSet rs = query(sql, params);

        Patient patient = new Patient();
        while (rs.next()) {
            patient.setPatientId(rs.getInt("patientId"));
            patient.setName(rs.getString("patientName"));
            patient.setDisease(rs.getString("disease"));
            patient.setSubmitDate(rs.getString("submitDate"));
            patient.setProcessingStatus(rs.getInt("processingStatus"));
            patient.setSex(rs.getString("sex"));
            patient.setAge(rs.getInt("age"));
            patient.setJob(rs.getString("job"));
            patient.setDate(rs.getString("date"));
            patient.setHospital(rs.getString("hospital"));
            patient.setDoctor(rs.getString("doctor"));
            patient.setDoctorPhoneNum(rs.getString("doctorPhoneNum"));
            patient.setBackground(rs.getString("background"));
            patient.setIllnessExperience(rs.getString("illnessExperience"));
            patient.setInsurance(rs.getString("insurance"));
            patient.setReimbursementRatios(rs.getString("reimbursementRatios"));
            patient.setFundRaisingChannel(rs.getString("fundRaisingChannel"));
            patient.setAmountReceived(rs.getString("amountReceived"));
            patient.setFixedAssets(rs.getString("fixedAssets"));
            patient.setFamilySize(rs.getInt("familySize"));
            patient.setCostGap(rs.getString("costGap"));
            patient.setHomeAddress(rs.getString("homeAddress"));
            patient.setContactName(rs.getString("contactName"));
            patient.setHelpLetter(rs.getString("helpLetter"));
            patient.setContactPhoneNum(rs.getString("contactPhoneNum"));
            patient.setContactWechat(rs.getString("contactWechat"));
            patient.setRelation(rs.getString("relation"));
        }
        // 释放资源
        close();
        return patient;
    }

    @Override
    public List<Patient> selectBySelective(Patient obj) throws Exception {
        return null;
    }

    @Override
    public List<Patient> selectAll() throws Exception {
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        String sql = "SELECT * FROM tb_patient_info";
        ResultSet rs = query(sql);
        // 处理结果
        List<Patient> patients = new ArrayList<Patient>();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setPatientId(rs.getInt("patientId"));
            patient.setName(rs.getString("patientName"));
            patient.setDisease(rs.getString("disease"));
            patient.setSubmitDate(rs.getString("submitDate"));
            patient.setProcessingStatus(rs.getInt("processingStatus"));
            patient.setSex(rs.getString("sex"));
            patient.setAge(rs.getInt("age"));
            patient.setJob(rs.getString("job"));
            patient.setDate(rs.getString("date"));
            patient.setHospital(rs.getString("hospital"));
            patient.setDoctor(rs.getString("doctor"));
            patient.setDoctorPhoneNum(rs.getString("doctorPhoneNum"));
            patient.setBackground(rs.getString("background"));
            patient.setIllnessExperience(rs.getString("illnessExperience"));
            patient.setInsurance(rs.getString("insurance"));
            patient.setReimbursementRatios(rs.getString("reimbursementRatios"));
            patient.setFundRaisingChannel(rs.getString("fundRaisingChannel"));
            patient.setAmountReceived(rs.getString("amountReceived"));
            patient.setFixedAssets(rs.getString("fixedAssets"));
            patient.setFamilySize(rs.getInt("familySize"));
            patient.setCostGap(rs.getString("costGap"));
            patient.setHomeAddress(rs.getString("homeAddress"));
            patient.setContactName(rs.getString("contactName"));
            patient.setHelpLetter(rs.getString("helpLetter"));
            patient.setContactPhoneNum(rs.getString("contactPhoneNum"));
            patient.setContactWechat(rs.getString("contactWechat"));
            patient.setRelation(rs.getString("relation"));

            patients.add(patient);
        }
        // 释放资源
        close();
        return patients;
    }

    @Override
    public List<Patient> selectBySelective(PatientQueryDto patientQueryDto) throws Exception {
        // 参数集合
        List params = new ArrayList();
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        StringBuilder sql = new StringBuilder("SELECT patientId,patientName,disease,submitDate,processingStatus FROM tb_patient_info WHERE TRUE");
        // 根据条件拼接查询参数
        getSelective(sql, params, patientQueryDto);
        // 分页查询
        if (patientQueryDto.getCurrentPage() != null && patientQueryDto.getPageSize() != null) {
            sql.append(" LIMIT ?,?");
            // 起始记录=（当前页-1）*页面大小
            params.add((patientQueryDto.getCurrentPage() - 1) * patientQueryDto.getPageSize());
            params.add(patientQueryDto.getPageSize());
        }
        ResultSet rs = query(sql.toString(), params.toArray());
        // 处理结果
        List<Patient> patients = new ArrayList<Patient>();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setPatientId(rs.getInt("patientId"));
            patient.setName(rs.getString("patientName"));
            patient.setDisease(rs.getString("disease"));
            patient.setSubmitDate(rs.getString("submitDate"));
            patient.setProcessingStatus(rs.getInt("processingStatus"));

            patients.add(patient);
        }
        // 释放资源
        close();
        return patients;
    }

    @Override
    public int countBySelective(PatientQueryDto patientQueryDto) throws Exception {
        // 参数集合
        List params = new ArrayList();
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM tb_patient_info WHERE TRUE");

        // 根据条件拼接查询参数
        getSelective(sql, params, patientQueryDto);

        ResultSet rs = query(sql.toString(), params.toArray());
        // 处理结果
        int count = 0;
        if (rs.next()) {
            count = rs.getInt("count");
        }
        // 释放资源
        close();
        return count;
    }

    private void getSelective(StringBuilder sql, List params, PatientQueryDto patientQueryDto) {
        /*
         * 根据条件拼接查询参数
         */
        if (patientQueryDto.getPatientId() != null) {
            sql.append(" AND patientId=?");
            params.add(patientQueryDto.getPatientId());
        }

        if (patientQueryDto.getPatientName() != null) {
            sql.append(" AND patientName LIKE ?");
            params.add("%"+patientQueryDto.getPatientName()+"%");
        }

        if (patientQueryDto.getDisease() != null) {
            sql.append(" AND disease LIKE ?");
            params.add("%"+patientQueryDto.getDisease()+"%");
        }

        if (patientQueryDto.getSubmitDate() != null) {
            sql.append(" AND submitDate=?");
            params.add(patientQueryDto.getSubmitDate());
        }

        if (patientQueryDto.getProcessState() != null) {
            sql.append(" AND processingStatus=?");
            params.add(patientQueryDto.getProcessState() + "");
        }
        sql.append(" ORDER BY patientId DESC");

    }
}
