package dao;

import dto.PatientQueryDto;
import entity.Patient;

import java.util.List;

public interface PatientDao extends BaseDao<Patient>{
    /**
     * 按条件查询
     * @param patientQueryDto
     * @return 结果集
     * @throws Exception
     */
    public List<Patient> selectBySelective(PatientQueryDto patientQueryDto)throws Exception;
    /**
     * 按条件统计记录数
     * @param patientQueryDto
     * @return 记录数
     * @throws Exception
     */
    int countBySelective(PatientQueryDto patientQueryDto) throws Exception;
    /**
     * 插入数据并获取主键
     * @param obj
     * @return 记录数
     * @throws Exception
     */
    public int insertGetKey(Patient obj) throws Exception;

    /**
     * 更新患者基本信息
     * @param obj
     * @return
     * @throws Exception
     */
    public int updatePatientInfo(Patient obj) throws Exception;
}
