package controller;

/**
 * 用户信息控制器
 * @author 丁渊彬
 * @date 2021-1-3
 */

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;
import vo.ResultVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserController extends BaseController {
	// 注入service
	private UserService userService = new UserServiceImpl();
/**
 * 登陆
 * @param req
 * @param resp
 * @throws Exception
 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 获取参数
		String userId = req.getParameter("userID");
		String password = req.getParameter("passWord");
		// 密码加密
		String token = DigestUtils.md5Hex(password);
		// 按用户名查询用户信息
		User user = userService.select(userId);
		/*
		 * 如果用户存在，比较密码 如果密码相同，登录成功，创建session 否则，抛出异常
		 */
		
		ResultVo resultVo = null;
		if (user != null) {

			if (user.getUserPassword().equals(token)) {
				// 创建session
				HttpSession session = req.getSession();
				// session失效时间60分钟（默认为30分钟）
				session.setMaxInactiveInterval(60 * 60);

				// 创建登录标记
				session.setAttribute("login", true);
				session.setAttribute("name", user.getUserName());
				// 用户身份验证通过，封装到resultVo
				resultVo = new ResultVo<User>(user);
			} else {
				resultVo = new ResultVo(402, "密码错误");
			}
		} else {
			resultVo = new ResultVo(401, "用户不存在或已禁用");
		}
		PrintWriter out = resp.getWriter();
		String json = JSON.toJSONString(resultVo);
		out.print(json);
	}
	/**
	 * 注册
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 获取参数
		String password = req.getParameter("passWord");
		String userName = req.getParameter("userName");
		String token = DigestUtils.md5Hex(password);
		User user=new User();
		user.setUserName(userName);
		user.setUserPassword(token);
		//保存注册信息
		userService.save(user);
		String newId=userService.getNewId();
		user.setUserId(newId);
		ResultVo resultVo = new ResultVo<User>(user);
		PrintWriter out = resp.getWriter();
		String json = JSON.toJSONString(resultVo);
		out.print(json);
	}
	/**
	 * 注销
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void cancellation(HttpServletRequest req, HttpServletResponse resp)throws Exception{
		// 创建session
		HttpSession session = req.getSession();
		session.invalidate();
	}
	
	public void getUserName(HttpServletRequest req, HttpServletResponse resp)throws Exception{
		HttpSession session = req.getSession();
		//获取用戶昵称
		String name=(String) session.getAttribute("name");
		PrintWriter out = resp.getWriter();
		String json = JSON.toJSONString(name);
		out.print(json);
	}
}
