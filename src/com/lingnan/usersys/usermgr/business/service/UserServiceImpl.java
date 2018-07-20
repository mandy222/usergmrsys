package com.lingnan.usersys.usermgr.business.service;

import java.sql.Connection;
import java.util.Vector;

import com.lingnan.usersys.common.constant.EnumType;
import com.lingnan.usersys.common.dao.DaoFactory;
import com.lingnan.usersys.common.exception.DaoException;
import com.lingnan.usersys.common.exception.ServiceException;
import com.lingnan.usersys.common.util.DBUtils;
import com.lingnan.usersys.usermgr.business.dao.UserDao;
import com.lingnan.usersys.usermgr.domain.UserVO;

/**
 * 用户service接口的实现类
 * @author 曾洁义
 *
 */
public class UserServiceImpl implements UserService {

	/**
	 * 创建用户service类实例
	 */
	private static UserService userService = new UserServiceImpl();
	
	/**
	 * 私有构造方法
	 */
	private UserServiceImpl() {
		
	}
	
	/**
	 * 取得用户service实例
	 * @return 实例对象
	 */
	public static UserService getInstance() {
		return userService;
	}
	
	/**
	 * 用户登陆
	 * @param _userName 用户名称
	 * @param _passwd 用户密码
	 * @return 用户对象
	 */
	public UserVO login(String _userName, String _passwd) {
		Connection conn = null;//声明数据库连接对象，用于保存数据连接对象
		UserVO user = null;//声明用户对象变量，用于保存登陆结果
		try{			
			conn = DBUtils.getConnection();//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);						
			user = userMgrDao.login(_userName, _passwd);//调用dao中的login方法，进行登陆操作，结果赋值给登陆结果变量		
		} catch(DaoException e) {
			throw e;//将自定义异常并抛出
		} catch(Exception e) {
			throw new ServiceException("用户登陆错误",e);//将异常封装成自定义异常并抛出
		} finally {
			DBUtils.closeConnection(conn);
		}	
		return user;//返回登陆结果
	}

	/**
	 * 添加或注册用户
	 * @param user 用户对象
	 * @return 添加用户成功返回true，否则返回false
	 */
	public boolean addUser(UserVO user) {
		Connection conn = null;//声明数据库连接对象，用于保存数据连接对象
		boolean flag = false;//声明变量，用于保存数据库插入结果
		try{			
			conn = DBUtils.getConnection();//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);						
			DBUtils.beginTransaction(conn);//调用数据库工具类的beginTransaction方法，开启事务
			flag = userMgrDao.addUser(user);//调用dao中的addUser方法，进行数据库插入操作，结果赋给结果变量flag
			DBUtils.commit(conn);//调用数据库工具类的commit方法，提交事务						
		} catch(DaoException e) {
			throw e;//将自定义异常并抛出
		} catch(Exception e) {
			DBUtils.rollback(conn);//调用数据库工具类的rollback方法，回滚事务
			throw new ServiceException("添加或注册用户错误",e);//将异常封装成自定义异常并抛出
		} finally {
			DBUtils.closeConnection(conn);//调用数据库工具类的closeConnection方法，关闭连接
		}		
		return flag;//返回数据库插入结果
	}

	/**
	 * 根据userID删除用户
	 * @param _userID 用户ID
	 * @return 成功返回true,失败返回false
	 */
	public boolean deleteUser(int _userID) {
		Connection conn = null;//声明数据库连接对象，用于保存数据连接对象
		boolean flag = false;//声明变量，用于保存数据库删除结果
		try{			
			conn = DBUtils.getConnection();//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);						
			DBUtils.beginTransaction(conn);//调用数据库工具类的beginTransaction方法，开启事务
			flag = userMgrDao.deleteUser(_userID);//调用dao中的deleteUser方法，进行数据库删除操作，结果赋给结果变量flag
			DBUtils.commit(conn);//调用数据库工具类的commit方法，提交事务						
		} catch(DaoException e) {
			throw e;//将自定义异常并抛出
		} catch(Exception e) {
			DBUtils.rollback(conn);//调用数据库工具类的rollback方法，回滚事务
			throw new ServiceException("删除用户时出现错误",e);//将异常封装成自定义异常并抛出
		} finally {
			DBUtils.closeConnection(conn);//调用数据库工具类的closeConnection方法，关闭连接
		}		
		return flag;//返回数据库删除结果
	}

	/**
	 * 查找所有用户的信息(当记录大于10条时分页显示查询结果，每页10条)
	 * @return 所有的用户信息
	 */
	public Vector<UserVO> findAllUserPageShow() {
		Connection conn = null;//声明数据库连接对象，用于保存数据连接对象
		Vector<UserVO> v = new Vector<UserVO>();//声明变量，用于保存数据库查询结果
		try{			
			conn = DBUtils.getConnection();//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);						
			DBUtils.beginTransaction(conn);//调用数据库工具类的beginTransaction方法，开启事务
			v = userMgrDao.findAllUserPageShow();//调用dao中的findAllUserPageShow方法，进行数据库查询操作，结果赋给结果变量v
			DBUtils.commit(conn);//调用数据库工具类的commit方法，提交事务						
		} catch(DaoException e) {
			throw e;//将自定义异常并抛出
		} catch(Exception e) {
			DBUtils.rollback(conn);//调用数据库工具类的rollback方法，回滚事务
			throw new ServiceException("查询所有用户信息(分页显示)时出现错误",e);//将异常封装成自定义异常并抛出
		} finally {
			DBUtils.closeConnection(conn);//调用数据库工具类的closeConnection方法，关闭连接
		}		
		return v;//返回数据库查询结果
	}

	/**
	 * 根据userID查询用户信息
	 * @param _userID 用户ID
	 * @return 用户信息
	 */
	public UserVO findUserByUserID(int _userID) {
		Connection conn = null;//声明数据库连接对象，用于保存数据连接对象
		UserVO user = null;//声明用户对象变量，用于保存登陆结果
		try{			
			conn = DBUtils.getConnection();//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);						
			user = userMgrDao.findUserByUserID(_userID);//调用dao中的findUserByUserID方法，进行查询操作，结果赋值给登陆结果变量		
		} catch(DaoException e) {
			throw e;//将自定义异常并抛出
		} catch(Exception e) {
			throw new ServiceException("根据userID查询用户信息时出现错误",e);//将异常封装成自定义异常并抛出
		} finally {
			DBUtils.closeConnection(conn);
		}	
		return user;//返回查询结果
	}
	
	/**
	 * 根据userName查询用户信息
	 * @param _userName 用户名称
	 * @return 符合条件的用户信息
	 */
	public Vector<UserVO> findUserByUserName(String _userName){
		Connection conn = null;//声明数据库连接对象，用于保存数据连接对象
		Vector<UserVO> v = new Vector<UserVO>();//声明变量，用于保存数据库查询结果
		try{			
			conn = DBUtils.getConnection();//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);						
			DBUtils.beginTransaction(conn);//调用数据库工具类的beginTransaction方法，开启事务
			v = userMgrDao.findUserByUserName(_userName);//调用dao中的findUserByUserName方法，进行数据库查询操作，结果赋给结果变量v
			DBUtils.commit(conn);//调用数据库工具类的commit方法，提交事务						
		} catch(DaoException e) {
			throw e;//将自定义异常并抛出
		} catch(Exception e) {
			DBUtils.rollback(conn);//调用数据库工具类的rollback方法，回滚事务
			throw new ServiceException("根据userName查询用户信息时出现错误",e);//将异常封装成自定义异常并抛出
		} finally {
			DBUtils.closeConnection(conn);//调用数据库工具类的closeConnection方法，关闭连接
		}		
		return v;//返回数据库插入结果
	}
	
	/**
	 * 更新用户信息
	 * @param user 用户信息
	 * @return 成功返回true,失败返回false
	 */
	public boolean updateUser(UserVO user){
		Connection conn = null;//声明数据库连接对象，用于保存数据连接对象
		boolean flag = false;//声明变量，用于保存数据库更新结果
		try{			
			conn = DBUtils.getConnection();//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);						
			DBUtils.beginTransaction(conn);//调用数据库工具类的beginTransaction方法，开启事务
			flag = userMgrDao.updateUser(user);//调用dao中的updateUser方法，进行数据库更新操作，结果赋给结果变量flag
			DBUtils.commit(conn);//调用数据库工具类的commit方法，提交事务						
		} catch(DaoException e) {
			throw e;//将自定义异常并抛出
		} catch(Exception e) {
			DBUtils.rollback(conn);//调用数据库工具类的rollback方法，回滚事务
			throw new ServiceException("更新用户信息时出现错误",e);//将异常封装成自定义异常并抛出
		} finally {
			DBUtils.closeConnection(conn);//调用数据库工具类的closeConnection方法，关闭连接
		}		
		return flag;//返回数据库更新结果
	}

	/**
	 * 根据信息状态查询用户信息
	 * @param _status 信息状态(1为有效，0为无效，2为全部信息)
	 * @return 返回符合信息状态的用户信息
	 */
	public Vector<UserVO> findAllUser(int _status) {		
		Connection conn = null;//声明数据库连接对象，用于保存数据连接对象
		Vector<UserVO> v = new Vector<UserVO>();//声明变量，用于保存数据库查询结果
		try{			
			conn = DBUtils.getConnection();//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);						
			DBUtils.beginTransaction(conn);//调用数据库工具类的beginTransaction方法，开启事务
			v = userMgrDao.findAllUser(_status);//调用dao中的findAllUser方法，进行数据库查询操作，结果赋给结果变量v
			DBUtils.commit(conn);//调用数据库工具类的commit方法，提交事务						
		} catch(DaoException e) {
			throw e;//将自定义异常并抛出
		} catch(Exception e) {
			DBUtils.rollback(conn);//调用数据库工具类的rollback方法，回滚事务
			throw new ServiceException("查询所有用户信息时出现错误",e);//将异常封装成自定义异常并抛出
		} finally {
			DBUtils.closeConnection(conn);//调用数据库工具类的closeConnection方法，关闭连接
		}		
		return v;//返回数据库查询结果
	}
	
	/**
	 * 分页查询
	 * @param pageNo 需要查询的页码数
	 * @param pageSize 每页显示记录的行数
	 * @return 符合分页要求的用户信息
	 */
	public Vector<UserVO> findUserPage(int pageNo, int pageSize){
		Connection conn = null;//声明数据库连接对象，用于保存数据连接对象
		Vector<UserVO> v = new Vector<UserVO>();//声明变量，用于保存数据库查询结果
		try{			
			conn = DBUtils.getConnection();//调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			//调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao userMgrDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);						
			DBUtils.beginTransaction(conn);//调用数据库工具类的beginTransaction方法，开启事务
			v = userMgrDao.findUserPage(pageNo, pageSize);//调用dao中的findAllUserPage方法，进行数据库查询操作，结果赋给结果变量v
			DBUtils.commit(conn);//调用数据库工具类的commit方法，提交事务						
		} catch(DaoException e) {
			throw e;//将自定义异常并抛出
		} catch(Exception e) {
			DBUtils.rollback(conn);//调用数据库工具类的rollback方法，回滚事务
			throw new ServiceException("分页查询时出现错误",e);//将异常封装成自定义异常并抛出
		} finally {
			DBUtils.closeConnection(conn);//调用数据库工具类的closeConnection方法，关闭连接
		}		
		return v;//返回数据库查询结果
	}

}
