package dao;

import entity.User;

public interface UserDao extends BaseDao<User>{
	public String getNewId() throws Exception;
}
