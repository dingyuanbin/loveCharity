package service.impl;

import dao.UserDao;
import dao.impl.UserDaoimpl;
import entity.User;
import service.UserService;

public class UserServiceImpl implements UserService{
	//注入dao
	private UserDao userDao=new UserDaoimpl();
	@Override
	public int save(User user) throws Exception {
		return userDao.insert(user);
	}

	@Override
	public int edit(User user) throws Exception {
		return userDao.update(user);
	}

	@Override
	public User select(String id) throws Exception {
		return userDao.selectById(id);
	}

	@Override
	public String getNewId() throws Exception {
		return userDao.getNewId();
	}
	
}
