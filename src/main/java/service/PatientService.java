package service;

import dto.PatientQueryDto;
import entity.Patient;
import utils.PageUtil;

import java.util.List;

public interface PatientService {
    /**
     * 分页查询
     * @param patientQueryDto
     * @return
     * @throws Exception
     */
    public PageUtil<Patient> pageList(PatientQueryDto patientQueryDto) throws Exception;
    /**
     * 查询所有数据
     * @return
     * @throws Exception
     */
    public List<Patient> findAll() throws Exception;

    /**
     * 保存数据
     * @param patient
     * @return
     * @throws Exception
     */
    public int save(Patient patient) throws Exception;

    /**
     * 根据id获取患者全部信息
     * @param id
     * @return
     * @throws Exception
     */
    public Patient findById(int id) throws Exception;

    /**
     * 删除数据
     * @param id
     * @return
     * @throws Exception
     */
    public int deleteById(int id)throws Exception;
    /**
     * 修改处理状态
     * @param patient
     * @return
     * @throws Exception
     */
    public int edit(Patient patient)throws Exception;

    /**
     * 修改所有基本信息
     * @param patient
     * @return
     * @throws Exception
     */
    public int editAllInfo(Patient patient)throws Exception;
}
