package dao.impl;

import dao.ImageInfoDao;
import entity.ImageInfo;
import entity.Patient;
import utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImageInfoDaoImpl extends JdbcUtil implements ImageInfoDao {


    @Override
    public int update(ImageInfo obj) throws Exception {
        return 0;
    }

    @Override
    public int delete(String id) throws Exception {
        return 0;
    }

    @Override
    public int delete(Integer id) throws Exception {
        return 0;
    }

    @Override
    public ImageInfo selectById(String id) throws Exception {
        return null;
    }
    @Override
    public List<ImageInfo> selectBySelective(int id)throws Exception{
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        String sql = "select * from tb_file_info where patientId=?";
        Object params[] = {id};
        ResultSet rs = query(sql, params);
        // 处理结果
        List<ImageInfo> imageInfos = new ArrayList<ImageInfo>();
        while (rs.next())
        {
            ImageInfo imageInfo =  new ImageInfo();
            imageInfo.setFileId(rs.getInt("fileId"));
            imageInfo.setPatientId(rs.getInt("patientId"));
            imageInfo.setFileName(rs.getString("fileName"));
            imageInfo.setFileLocalUrl(rs.getString("fileLocalUrl"));
            imageInfo.setCategory(rs.getString("category"));

            imageInfos.add(imageInfo);
        }
        close();
        return imageInfos;
    }

    @Override
    public int deleteByFileLocalUrl(String fileLocalUrl) throws Exception {
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        String sql = "delete from tb_file_info " + " WHERE fileLocalUrl='" + fileLocalUrl + "'";
        Object[] params = {};
        int rows = update(sql, params);
        // 释放资源
        close();
        return rows;
    }

    @Override
    public int insert(ImageInfo obj) throws Exception {
        int rows=0;
        // 获取连接
        getConnection("db_loveCharity.properties");
        // 发送sql
        String sql="INSERT INTO tb_file_info(patientId,fileName,fileLocalUrl,category)" + " VALUES(?,?,?,?)";
        Object[] params = { obj.getPatientId(), obj.getFileName(), obj.getFileLocalUrl(),obj.getCategory() };
        rows = update(sql, params);
        // 释放资源
        close();
        return rows;
    }

    @Override
    public ImageInfo selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<ImageInfo> selectBySelective(ImageInfo obj) throws Exception {

        return null;
    }

    @Override
    public List<ImageInfo> selectAll() throws Exception {
        return null;
    }
}
