package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import entity.ImageInfo;
import utils.ImageProcessing;
import utils.StringUtil;

import dto.FileDto;

import com.alibaba.fastjson.JSON;

import vo.ResultVo;

@WebServlet("/file")
@MultipartConfig
public class UploadFileController extends BaseController {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileInputStream fis;
	/**
     * 文件上传
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
	protected void upload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * 获取前端传递的文件
		 * Part getPart(String name): 用于获取请求中指定name的文件
		 * Coolection<Part> getParts();获取请求中全部的文件
		 */
		Part part = req.getPart("file");
		
		/*
		 * 用时间戳给文件重命名，避免文件重名而覆盖
		 * 先获取文件后缀名，即文件类型
		 * 新文件名=时间戳+后缀名
		 */
		String realFileName=part.getSubmittedFileName();//获取文件名
		String suffix=realFileName.substring(realFileName.lastIndexOf("."));			
		String fileName=System.currentTimeMillis()+suffix;
		Calendar calendar = Calendar.getInstance();
		// 获取当前年
		int year = calendar.get(Calendar.YEAR);  
		// 获取当前月
		int month = calendar.get(Calendar.MONTH) + 1;
		/*
		 * 将文件保存到tomcat/upload目录下
		 * 如果不存在该文件夹，创建文件夹
		 */
		String tomcat=System.getProperty("catalina.home");
		String path=tomcat+File.separator+"upload"+File.separator+year+"_"+month;
		File folder=new File(path);
		if(!folder.exists()){
			folder.mkdir();
		}
		//保存文件
		String fileUrl=path+File.separator+fileName;
		
		InputStream inputStream = part.getInputStream();
		/*
		 * 压缩图片并加上水印
		 */
		ImageProcessing imageProcessing=new ImageProcessing();
		imageProcessing.process(inputStream,fileUrl);

		//将对象封装到resultVo

		FileDto fileDto=new FileDto(fileName, year+"_"+month+File.separator+fileName);
		ResultVo<FileDto> resultVo=new ResultVo<FileDto>(fileDto);
		String json = JSON.toJSONString(resultVo);
		// 响应结果
		PrintWriter out = resp.getWriter();
		out.print(json);
	}
	/**
     * 文件下载
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
	protected void download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//客户端传递的需要下载的文件名
		String url = StringUtil.toNull(req.getParameter("url"));
		/*
		 * 文件保存在tomcat/upload目录下
		 * 从保存位置下载文件
		 */
		String tomcat=System.getProperty("catalina.home");
		String path=tomcat+File.separator+"upload"+url;
		fis = new FileInputStream(path);
        resp.setCharacterEncoding("utf-8");
        //以附件的方式另存文件
        resp.setHeader("Content-Disposition", "attachment; filename="+url.split(File.separator)[1]);
        ServletOutputStream out = resp.getOutputStream();
        byte[] bt = new byte[1024];
        int length = 0;
        while((length=fis.read(bt))!=-1){
            out.write(bt,0,length);           
        }
        out.close();
	}
	
	protected void delect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//客户端传递的需要删除的文件名
		String url = StringUtil.toNull(req.getParameter("url"));

		/*
		* 文件保存在tomcat/upload目录下
		* 从保存位置删除文件
		*/
		String tomcat=System.getProperty("catalina.home");
		String path=tomcat+File.separator+"upload"+File.separator+url;
		File file=new File(path);
		String json="";
		//判断文件是否存在 
		if(file.exists()==true) {
			if(file.delete()) {
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

	/**
	 * 删除所有未提交的图片
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//客户端传递的需要下载的文件名
		String fileListString = StringUtil.toNull(req.getParameter("fileListString"));

		/*
		 * 文件保存在tomcat/upload目录下
		 * 从保存位置删除文件
		 */
		String tomcat = System.getProperty("catalina.home");
		String json = "";
		String image[] = fileListString.split("#");
		for (int i = 0; i < image.length; i++) {
			String attribute[] = image[i].split(",");
			String last[] = attribute[1].split(":");
			String path = tomcat + File.separator + "upload" + File.separator + last[1];
			File file = new File(path);
			//判断文件是否存在
			if (file.exists() == true) {
				file.delete();
			}
		}
	}


}
