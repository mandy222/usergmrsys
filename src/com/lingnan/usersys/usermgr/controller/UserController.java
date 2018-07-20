package com.lingnan.usersys.usermgr.controller;

import java.util.Vector;

import com.lingnan.usersys.usermgr.business.service.UserService;
import com.lingnan.usersys.usermgr.business.service.UserServiceImpl;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用户控制类
 * @author 曾洁义
 *
 */
public class UserController {
	UserService userMgrService = UserServiceImpl.getInstance();//声明用户service接口对象，用于业务处理
	/**
	 * 用户登陆
	 * @param _userName 用户名称
	 * @param _passwd 用户密码
	 * @return 用户对象
	 */
	public UserVO doLogin(String _userName,String _passwd) {
		UserVO user = null;
		try{
			user = userMgrService.login(_userName, _passwd);//调用用户service接口中的login方法，进行用户登陆操作
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}		
		return user;
	}
	
	/**
	 * 注册或添加用户
	 * @param user 用户对象
	 * @return 成功时返回true，失败时返回false
	 */
	public boolean doAddUser(UserVO user) {
		boolean flag = false;
		try{
			flag = userMgrService.addUser(user);//调用用户service接口中的addUser方法，进行用户注册或添加操作
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}		
		return flag;
	}
	
	/**
	 * 根据userID删除用户
	 * @param _userID 用户ID
	 * @return 成功返回true,失败返回false
	 */
	public boolean doDeleteUser(int _userID) {
		boolean flag = false;
		try{
			flag = userMgrService.deleteUser(_userID);//调用用户service接口中的deleteUser方法，进行用户删除操作
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}		
		return flag;
	}
	
	/**
	 * 查找所有用户的信息(当记录大于10条时分页显示查询结果，每页10条)
	 * @return 所有的用户信息
	 */
	public Vector<UserVO> doFindAllUserPageShow(){
		Vector<UserVO> v = new Vector<UserVO>();
		try{
			v = userMgrService.findAllUserPageShow();//调用用户service接口中的findAllUser方法，进行查询所有用户操作
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}		
		return v;		
	}
	
	/**
	 * 分页查询
	 * @param pageNo 需要查询的页码数
	 * @param pageSize 每页显示记录的行数
	 * @return 符合分页要求的用户信息
	 */
	public Vector<UserVO> doFindUserPage(int pageNo, int pageSize){
		Vector<UserVO> v = new Vector<UserVO>();
		try{
			v = userMgrService.findUserPage(pageNo, pageSize);//调用用户service接口中的findAllUserPage方法，进行分页查询操作
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}		
		return v;		
	}
	
	/**
	 * 根据信息状态查询用户信息
	 * @param _status 信息状态(1为有效，0为无效，2为全部信息)
	 * @return 返回符合信息状态的用户信息
	 */
	public Vector<UserVO> doFindAllUser(int _status) {		
		Vector<UserVO> v = new Vector<UserVO>();
		try{
			v = userMgrService.findAllUser(_status);//调用用户service接口中的findAllUser方法，进行查询所有用户操作
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}		
		return v;	
	}
	
	/**
	 * 根据userID查询用户信息
	 * @param _userID 用户ID
	 * @return 用户信息
	 */
	public UserVO doFindUserByUserID(int _userID) {
		UserVO user = null;
		try{
			user = userMgrService.findUserByUserID(_userID);//调用用户service接口中的findUserByUserID方法，进行用户查询操作
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}		
		return user;
	}
	
	/**
	 * 根据userName查询用户信息
	 * @return
	 */
	public Vector<UserVO> doFindUserByUserName(String _userName){
		Vector<UserVO> v = new Vector<UserVO>();
		try{
			v = userMgrService.findUserByUserName(_userName);//调用用户service接口中的findUserByUserName方法，根据userName查询用户信息
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}		
		return v;	
	}
	
	/**
	 * 更新用户信息
	 * @param user 用户信息
	 * @return 成功返回true,失败返回false
	 */
	public boolean doUpdateUser(UserVO user){
		boolean flag = false;
		try{
			flag = userMgrService.updateUser(user);//调用用户service接口中的updateUser方法，进行用户更新操作
		} catch(Exception e){
			System.out.println(e.getMessage());//显示异常信息
		}		
		return flag;
	}
}
