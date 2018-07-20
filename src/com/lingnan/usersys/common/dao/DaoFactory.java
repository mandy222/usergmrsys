package com.lingnan.usersys.common.dao;

import java.sql.Connection;

import com.lingnan.usersys.common.exception.ServiceException;
import com.lingnan.usersys.usermgr.business.dao.UserDaoImpl;

/**
 * dao工厂类
 * @author 曾洁义
 *
 */
public class DaoFactory {
	/**
	 * 获得用户dao对象的工厂方法
	 * @param conn 数据库连接对象
	 * @param type dao常量
	 * @return dao接口
	 */
	public static BaseDao getDao(Connection conn,String type) {		
		if("user".equals(type)) {//如果传入的dao类型是用户user，就实例化用户dao实现类
			return new UserDaoImpl(conn);//返回实例化的dao对象
		}	
		else {//如果没有和以上指定类型匹配的值，就提示错误信息
			throw new ServiceException("dao工厂方法出错");
		}
	}
}
