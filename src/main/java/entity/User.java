package entity;
/**
 * 
 * @author 丁渊彬
 * @date 2021-1-3
 */
public class User {
	private static final long serialVersionUID =  1L;
	/**
	 * 用户账号
	 */
	private String userId;
	/**
	 * 密码
	 */
	private String userPassword;
	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 头像
	 */
	private String headPortrait;
	/**
	 * 已删除
	 */
	private Integer userIsDelete;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public Integer getUserIsDelete() {
		return userIsDelete;
	}
	public void setUserIsDelete(Integer userIsDelete) {
		this.userIsDelete = userIsDelete;
	}
}
