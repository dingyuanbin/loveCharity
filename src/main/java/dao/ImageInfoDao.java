package dao;

import dto.PatientQueryDto;
import entity.ImageInfo;
import entity.Patient;

import java.util.List;

public interface ImageInfoDao extends BaseDao<ImageInfo>{
    /**
     * 按id查询
     * @param id
     * @return 结果集
     * @throws Exception
     */
    public List<ImageInfo> selectBySelective(int id)throws Exception;

    /**
     * 按存储地址删除图片
     * @param fileLocalUrl
     * @throws Exception
     */
    public int deleteByFileLocalUrl(String fileLocalUrl)throws Exception;
}
