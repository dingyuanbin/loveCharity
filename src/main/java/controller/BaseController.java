package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import vo.ResultVo;
/**
 * 分发器
 * @author ke
 * @version 1.0,2020-11-11
 *
 */
public class BaseController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG=Logger.getLogger(BaseController.class);
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//获取方法的名字
		String action=req.getParameter("action");
		/*
		 * 通过java反射调用方法
		 */
		try {
			//把方法名转换成方法
			Method method=this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
			//给私有方法授权
			method.setAccessible(true);
			//调用方法
			method.invoke(this, req,resp);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			ResultVo resultVo = new ResultVo(500, "未知服务错误");
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html; charset=utf-8");
			String json = JSON.toJSONString(resultVo);
			// 响应结果
			PrintWriter out = resp.getWriter();
			out.print(json);
		} 
		
	}

}
