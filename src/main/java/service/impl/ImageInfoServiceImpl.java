package service.impl;

import dao.ImageInfoDao;
import dao.PatientDao;
import dao.impl.ImageInfoDaoImpl;
import dao.impl.PatientDaoImpl;
import entity.ImageInfo;
import service.ImageInfoService;

import java.util.ArrayList;
import java.util.List;

public class ImageInfoServiceImpl implements ImageInfoService {
    //注入dao
    private ImageInfoDao imageInfoDao = new ImageInfoDaoImpl();

    @Override
    public int save(List<ImageInfo> imageInfos) throws Exception {
        if(imageInfos==null)
            return 0;
        int rows = 0;
        for (int i = 0; i < imageInfos.size(); i++) {
            rows += imageInfoDao.insert(imageInfos.get(i));
        }
        return rows;
    }

    @Override
    public List<ImageInfo> findById(int id) throws Exception {
        return imageInfoDao.selectBySelective(id);
    }

    @Override
    public List<ImageInfo> getImageInfoList(String imageInfoList, int key) throws Exception {
        if(imageInfoList==null)
            return null;
        List<ImageInfo> imageInfos = new ArrayList<ImageInfo>();
        String image[] = imageInfoList.split("#");
        for (int i = 0; i < image.length; i++) {
            String attribute[] = image[i].split(",");
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setPatientId(key);
            String last[] = attribute[0].split(":");
            imageInfo.setCategory(last[1]);
            last = attribute[1].split(":");
            imageInfo.setFileLocalUrl(last[1]);
            last = attribute[2].split(":");
            imageInfo.setFileName(last[1]);

            imageInfos.add(imageInfo);
        }
        return imageInfos;
    }

    @Override
    public int deleteOld(String url) throws Exception {
        return imageInfoDao.deleteByFileLocalUrl(url);
    }
}
