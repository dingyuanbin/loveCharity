package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginFilter implements Filter{
	private String excludeUrl;
    /**
     * 获取初始化参数
     */
	public void init(FilterConfig filterConfig) throws ServletException {
		//获取初始化参数
		excludeUrl=filterConfig.getInitParameter("excludeUrl");		
	}
    /**
     * 过滤处理
     */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		//获取url参数 action=login&xxxx=dd&ddd
		String action=req.getQueryString();
		String urlParams=req.getRequestURI()+"?"+action;
		/*
		 * 如果过滤器为login，不拦截请求
		 * 否则：
		 *   如果用户已登录，不拦截请求
		 *   否则，输出错误信息
		 */
//		System.out.println("urlParams"+urlParams);
//		System.out.println("excludeUrl"+excludeUrl);

		if(urlParams.contains("administration")){
			//是否登录
			HttpSession session=req.getSession();
			if(session.getAttribute("login")!=null ){
				chain.doFilter(request, response);//进入下一个过滤链
			}else{
				resp.sendRedirect("login.html");
			}
		}
		else {
			chain.doFilter(request, response);//进入下一个过滤链
		}

//		if(urlParams!=null && (urlParams.contains(excludeUrl))){
//			chain.doFilter(request, response);//进入下一个过滤链
//		}else{
//			//是否登录
//			HttpSession session=req.getSession();
//			if(session.getAttribute("login")!=null ){
//				chain.doFilter(request, response);//进入下一个过滤链
//			}else{
//				resp.sendRedirect("login.html");
//			}
//		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
