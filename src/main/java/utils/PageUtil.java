package utils;

import java.util.List;

/**
 * 分页控件
 * @author ke
 * @version 1.0,2020-11-5
 *
 */
public class PageUtil<T> {
	/**
	 * 当前页
	 */
	private Integer currentPage;
	/**
	 * 页面大小
	 */
	private Integer pageSize;
	/**
	 * 总记录数
	 */
	private Integer total;
	/**
	 * 列表数据
	 */
	List<T> data;
	/**
	 *  无参构造方法
	 */
	public PageUtil() {
		super();
	}
	/**
	 * 有参构造方法
	 * @param currentPage
	 * @param pageSize
	 * @param total
	 * @param data
	 */
	public PageUtil(Integer currentPage, Integer pageSize, Integer total, List<T> data) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.total = total;
		this.data = data;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	

}
