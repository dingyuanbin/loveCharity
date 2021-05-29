package service;

import entity.ImageInfo;
import entity.Patient;

import java.util.List;

public interface ImageInfoService {

    /**
     * 保存数据
     * @param imageInfos
     * @return
     * @throws Exception
     */
    public int save(List<ImageInfo> imageInfos) throws Exception;


    /**
     * 查询所有数据
     * @return
     * @throws Exception
     */
    public List<ImageInfo> findById(int id) throws Exception;

    /**
     * 将图片列表信息分解成单独的信息
     * @param imageInfoList
     * @return
     * @throws Exception
     */
    public List<ImageInfo> getImageInfoList(String imageInfoList,int key) throws Exception;

    /**
     * 删除原有图片信息
     * @param url
     * @return
     * @throws Exception
     */
    public int deleteOld(String url) throws Exception;
}
