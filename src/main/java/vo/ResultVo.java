package vo;

import java.io.Serializable;

/**
 * 响应对象
 * @author ke
 * @version 1.0,2020-11-7
 *
 */
public class ResultVo<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 响应编码
	 */
	private Integer code;
	/**
	 * 响应信息
	 */
	private String msg;
	/**
	 * 响应数据
	 */
	private T data;
	/**
	 * 无参构造方法
	 */
	public ResultVo() {
		super();
	}
	/**
	 * 获取数据成功
	 * @param data
	 */
	public ResultVo(T data) {
		super();
		this.code=0;
		this.msg="成功";
		this.data = data;
	}
	/**
	 * 获取数据失败
	 * @param code
	 * @param msg
	 */
	public ResultVo(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
	
	

}
