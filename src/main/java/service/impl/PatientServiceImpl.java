package service.impl;

import dao.PatientDao;
import dao.impl.PatientDaoImpl;
import dto.PatientQueryDto;
import entity.Patient;
import service.PatientService;
import utils.PageUtil;

import java.util.List;

public class PatientServiceImpl implements PatientService {
    //注入dao
    private PatientDao patientDao = new PatientDaoImpl();

    @Override
    public PageUtil<Patient> pageList(PatientQueryDto patientQueryDto) throws Exception {
        /*
         * 统计总记录数
         * 如果总记录数>0：
         *  （1）查询列表数据
         *  （2）封装分页数据
         */
        PageUtil pageUtil = null;
        int count = patientDao.countBySelective(patientQueryDto);

        List<Patient> patients = patientDao.selectBySelective(patientQueryDto);
        pageUtil = new PageUtil<Patient>(patientQueryDto.getCurrentPage(), patientQueryDto.getPageSize(), count, patients);

        return pageUtil;
    }

    @Override
    public List<Patient> findAll() throws Exception {
        return patientDao.selectAll();
    }

    @Override
    public int save(Patient patient) throws Exception {
        return patientDao.insertGetKey(patient);
    }

    @Override
    public Patient findById(int id) throws Exception {
        return patientDao.selectById(id);
    }

    @Override
    public int deleteById(int id) throws Exception {
        return patientDao.delete(id);
    }

    @Override
    public int edit(Patient patient) throws Exception {
        return patientDao.update(patient);
    }

    @Override
    public int editAllInfo(Patient patient) throws Exception {
        return patientDao.updatePatientInfo(patient);
    }

}
