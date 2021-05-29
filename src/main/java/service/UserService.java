package service;

import entity.User;

/**
 * 
 * @author 丁渊彬
 * @date 2021-1-3
 */
public interface UserService {
	/**
	 * 添加信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int save(User user) throws Exception;
	/**
	 * 编辑信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int edit(User user) throws Exception; 
	/**
	 * 查询信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User select(String id) throws Exception; 
	/**
	 * 最新的账号
	 * @return
	 * @throws Exception
	 */
	public String getNewId() throws Exception; 
	
}
