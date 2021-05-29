package controller;

import com.alibaba.fastjson.JSON;
import entity.ImageInfo;
import entity.Patient;
import service.ImageInfoService;
import service.impl.ImageInfoServiceImpl;
import utils.PageUtil;
import utils.StringUtil;
import vo.ResultVo;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/imageList")
public class ImageInfoController  extends BaseController {
    // 注入service
    ImageInfoService imageInfoService=new ImageInfoServiceImpl();
    /**
     * 根据id获取照片全部信息
     * @param request
     * @param response
     * @throws Exception
     */
    private void getImageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 接收请求
        int patientId = StringUtil.toInt(StringUtil.toNull(request.getParameter("patientId")));
        // 处理结果
        List<ImageInfo> imageInfos= imageInfoService.findById(patientId);
        // 处理结果
        PageUtil pageUtil;
        ResultVo resultVo;
        resultVo = new ResultVo<List<ImageInfo>>(imageInfos);
        String json = JSON.toJSONString(resultVo);
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    /**
     * 删除原有图片
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delectOld(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //客户端传递的需要删除的文件名
        String url = StringUtil.toNull(req.getParameter("url"));

        /*
         * 文件保存在tomcat/upload目录下
         * 从保存位置删除文件
         */
        String tomcat=System.getProperty("catalina.home");
        String path=tomcat+ File.separator+"upload"+File.separator+url;
        File file=new File(path);
        String json="";
        //判断文件是否存在
        if(file.exists()==true) {
            if(file.delete()) {
                imageInfoService.deleteOld(url);
                json = JSON.toJSONString(new ResultVo<String>(0, "success"));
            }
            else {
                json = JSON.toJSONString(new ResultVo<String>(500, "fail to delete"));
            }
        }
        else {
            json = JSON.toJSONString(new ResultVo<String>(500, "error:file no exist"));
        }
        PrintWriter out = resp.getWriter();
        out.print(json);
    }
}
