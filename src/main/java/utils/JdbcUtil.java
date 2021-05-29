package utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 * 
 * @author ke
 * @version 1.0,2020-10-31
 */
public class JdbcUtil {
	// 日志管理器
	private static final Logger LOG = Logger.getLogger(JdbcUtil.class);
	/**
	 * 连接
	 */
	private Connection conn;
	/**
	 * sql声明
	 */
	private PreparedStatement ps;
	/**
	 * 结果集
	 */
	private ResultSet rs;

	/**
	 * 获取连接
	 * 
	 * @param configFile
	 *            连接参数配置文件
	 * @throws Exception
	 */
	public void getConnection(String configFile) throws Exception {

		try {
			/*
			 * 读取配置文件： 从配置文件获取参数 读取配置文件: JdbcUtil.class.getClassLoader()获取资源路径
			 * getResourceAsStream()将resource转换成输入
			 */

			InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream(configFile);
			Properties properties = new Properties();
			properties.load(is);
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			// 加载驱动
			Class.forName(driver);
			// 获取连接
			conn = DriverManager.getConnection(url, user, password);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new Exception("配置文件没找到");
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage(), e);
			throw new Exception("驱动程序没找到或驱动程序错误");
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new Exception("连接地址或用户名和密码错误");
		}

	}

	/**
	 * 更新
	 * 
	 * @param sql
	 *            sql语句
	 * @return 影响的行数
	 * @throws Exception
	 */
	public int update(String sql) throws Exception {
		int rows = 0;
		try {
			// 发送sql
			ps = conn.prepareStatement(sql);
			// 执行
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new Exception("sql语法错误");
		}
		return rows;
	}

	/**
	 * 带参数更新
	 *
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 主键
	 * @throws Exception
	 */
	public int updateGetKey(String sql, Object[] params) throws Exception {
		int primaryKey = 0;
		try {
			// 发送sql
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			// 参数赋值
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 执行
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			//获取主键
			if(rs.next()){
				primaryKey=rs.getInt(1);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new Exception("sql语法错误");
		}
		return primaryKey;
	}
	/**
	 * 带参数更新
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 影响的行数
	 * @throws Exception
	 */
	public int update(String sql, Object[] params) throws Exception {
		int rows = 0;
		String primaryKey = null;
		try {
			// 发送sql
			ps = conn.prepareStatement(sql);
			// 参数赋值
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 执行
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new Exception("sql语法错误");
		}
		return rows;
	}

	/**
	 * 查询
	 * 
	 * @param sql
	 *            sql语句
	 * @return 结果集
	 * @throws Exception
	 */
	public ResultSet query(String sql) throws Exception {
		try {
			// 发送sql
			ps = conn.prepareStatement(sql);
			// 执行
			rs = ps.executeQuery();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new Exception("sql语法错误");
		}
		return rs;
	}

	/**
	 * 带参数查询
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数数组
	 * @return 结果集
	 * @throws Exception
	 */
	public ResultSet query(String sql, Object[] params) throws Exception {
		try {
			// 发送sql
			ps = conn.prepareStatement(sql);
			// 参数赋值
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 执行
			rs = ps.executeQuery();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			throw new Exception("sql语法错误");
		}
		return rs;
	}
	/**
	 * 释放资源
	 */
	public void close() throws Exception{
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();
	}

}
