package dao;

import java.util.List;

/**
 * 泛型接口
 * @author ke
 * @version 1.0,2020-10-31
 */
public interface BaseDao<T> {
	/**
	 * 插入数据
	 * @param obj 数据对象
	 * @return 影响行数
	 * @throws Exception
	 */
	int insert(T obj) throws Exception;
	/**
	 * 修改数据
	 * @param obj 数据对象
	 * @return 影响行数
	 * @throws Exception
	 */
	int update(T obj) throws Exception;
	/**
	 * 删除数据
	 * @param id 主键
	 * @return 影响行数
	 * @throws Exception
	 */
	int delete(String id) throws Exception;
	/**
	 * 删除数据
	 * @param id 主键
	 * @return 影响行数
	 * @throws Exception
	 */
	int delete(Integer id) throws Exception;
	/**
	 * 按主键查询
	 * @param id 主键
	 * @return 对象
	 * @throws Exception
	 */
	T selectById(String id) throws Exception;
	/**
	 * 按主键查询
	 * @param id 主键
	 * @return 对象
	 * @throws Exception
	 */
	T selectById(Integer id) throws Exception;
	/**
	 * 按条件查询
	 * @param obj 对象
	 * @return 结果集合
	 * @throws Exception
	 */
	List<T> selectBySelective(T obj) throws Exception;
	/**
	 * 查询所有数据
	 * @return
	 * @throws Exception
	 */
	List<T> selectAll() throws Exception;

}
