package com.lingnan.usersys.usermgr.business.service;

import java.util.Vector;

import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用户service接口
 * @author 曾洁义
 *
 */
public interface UserService {
	
	/**
	 * 用户登陆
	 * @param _userName 用户姓名
	 * @param passwd 用户密码
	 * @return 用户对象
	 */
	public UserVO login(String _userName, String _passwd);
	
	/**
	 * 添加或注册用户
	 * @param user 用户对象
	 * @return 添加成功返回true,失败返回false
	 */
	public boolean addUser(UserVO user);
	
	/**
	 * 根据userID删除用户
	 * @param _userID 用户ID
	 * @return 成功返回true,失败返回false
	 */
	public boolean deleteUser(int _userID);
	
	/**
	 * 查找所有用户的信息(当记录大于10条时分页显示查询结果，每页10条)
	 * @return 所有的用户信息
	 */
	public Vector<UserVO> findAllUserPageShow();
	
	/**
	 * 分页查询
	 * @param pageNo 需要查询的页码数
	 * @param pageSize 每页显示记录的行数
	 * @return 符合分页要求的用户信息
	 */
	public Vector<UserVO> findUserPage(int pageNo, int pageSize);
	
	/**
	 * 根据信息状态查询用户信息
	 * @param _status 信息状态(1为有效，0为无效，2为全部信息)
	 * @return 返回符合信息状态的用户信息
	 */
	public Vector<UserVO> findAllUser(int _status) ;
	
	/**
	 * 根据userID查询用户信息
	 * @param _userID 用户ID
	 * @return 用户信息
	 */
	public UserVO findUserByUserID(int _userID);
	
	/**
	 * 根据userName查询用户信息
	 * @param _userName 用户名称
	 * @return 符合条件的用户信息
	 */
	public Vector<UserVO> findUserByUserName(String _userName);
	
	/**
	 * 更新用户信息
	 * @param user 用户信息
	 * @return 成功返回true,失败返回false
	 */
	public boolean updateUser(UserVO user);
	
}
