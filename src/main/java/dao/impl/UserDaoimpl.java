package dao.impl;

import dao.UserDao;
import entity.User;
import utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.List;

public class UserDaoimpl extends JdbcUtil implements UserDao {

	@Override
	public int insert(User obj) throws Exception {
		// 获取连接
		getConnection("db_loveCharity.properties");
		// 发送sql
		String sql = "INSERT INTO tb_user(u_password,u_name)" + " VALUES(?,?)";
		Object[] params = { obj.getUserPassword(), obj.getUserName() };
		int rows = update(sql, params);
		// 释放资源
		close();
		return rows;
	}

	@Override
	public int update(User obj) throws Exception {
		// 获取连接
		getConnection("db_loveCharity.properties");
		// 获取账号
		String userId = obj.getUserId();
		// 发送sql
		String sql = "UPDATE tb_user" + " SET u_password=?,u_name=?" + " WHERE u_id='" + userId + "'";
		Object[] params = { obj.getUserPassword(), obj.getUserName() };
		int rows = update(sql, params);
		// 释放资源
		close();
		return rows;
	}

	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectById(String id) throws Exception {
		getConnection("db_loveCharity.properties");
		String sql = "SELECT * from tb_user where u_id = '" + id + "'";
		ResultSet rs = query(sql);
		User user =null;
		if(rs.next()) {
			user=new User();
			if (rs.getString("u_is_delete").equals("0")) {
				user.setUserId(rs.getString("u_id"));
				user.setUserPassword(rs.getString("u_password"));
				user.setUserName(rs.getString("u_name"));
				user.setHeadPortrait(rs.getString("u_headPortrait"));
			}
		}
		close();
		return user;
	}

	@Override
	public User selectById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectBySelective(User obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNewId() throws Exception {
		getConnection("db_loveCharity.properties");
		String sql = "select u_id from tb_user order by u_id desc limit 1";
		ResultSet rs = query(sql);
		String newIdString=null;
		if(rs.next()) {
			newIdString=rs.getString("u_id");
		}
		close();
		return newIdString;
	}

}
